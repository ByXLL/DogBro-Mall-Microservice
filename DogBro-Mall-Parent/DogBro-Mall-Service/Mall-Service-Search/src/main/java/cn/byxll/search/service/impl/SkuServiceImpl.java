package cn.byxll.search.service.impl;

import cn.byxll.goods.dto.SearchParam;
import cn.byxll.search.dao.SkuEsMapper;
import cn.byxll.goods.feign.SkuFeign;
import cn.byxll.goods.pojo.Sku;
import cn.byxll.search.service.SkuService;
import cn.byxll.search.SkuInfo;
import com.alibaba.fastjson.JSON;
import entity.Result;
import entity.StatusCode;
import exception.OperationalException;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SkuService 接口实现类
 * @author By-Lin
 */
@Service
public class SkuServiceImpl implements SkuService {
    private final SkuEsMapper skuEsMapper;
    private final SkuFeign skuFeign;
    /**
     * ElasticsearchTemplate 可以实现索引库的增删改查[高级搜索]
     */
    private final ElasticsearchTemplate elasticsearchTemplate;

    public SkuServiceImpl(SkuEsMapper skuEsMapper, SkuFeign skuFeign, ElasticsearchTemplate elasticsearchTemplate) {
        this.skuEsMapper = skuEsMapper;
        this.skuFeign = skuFeign;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    /**
     * 导入sku数据
     * @return      响应数据
     */
    @Override
    @Transactional(rollbackFor = OperationalException.class)
    public Result<Boolean> importSkuData() {
        // 调用feign 查询 sku 集合
        Result<List<Sku>> skuResult = skuFeign.findAll();

        // 将List<Sku>转成List<SkuInfo>
        List<SkuInfo> skuInfoList = JSON.parseArray(JSON.toJSONString(skuResult.getData()), SkuInfo.class);

        // 循环当前的 skuInfoList
        for (SkuInfo skuInfo : skuInfoList) {
            // 获取spec -> Map(String)-> Map类型 {} {"电视音响效果":"立体声","电视屏幕尺寸":"20英寸","尺码":"165"}
            Map<String, Object> specMap = JSON.parseObject(skuInfo.getSpec(), Map.class);

            // 如果需要生成动态的域，只需要将该域存入到一个 Map<String, Object>  对象中，该 Map<String, Object>  的key会生成一个域，域的名字就是该域的key
            // 在SkuInfo -> specMap 字段就是用于存放该数据
            skuInfo.setSpecMap(specMap);
        }
        // 调用spring data elasticsearch的API 导入到ES中
        skuEsMapper.saveAll(skuInfoList);
        return new Result<>(true, StatusCode.OK, "导入数据到索引库中成功");
    }

    /**
     * 条件查询
     *
     * @param searchMap 查询对象map
     * @return          响应数据
     */
    @Override
    public Result<Map<String, Object>> search(Map<String,String> searchMap) {
        // 构建搜索对象
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        if(searchMap != null) {
            // 根据关键词搜索
            if(StringUtils.isNotBlank(searchMap.get("keyWords"))) {
                // field 对应的是es 数据库列名
                queryBuilder.withQuery(QueryBuilders.queryStringQuery(searchMap.get("keyWords")).field("name"));
            }
            // 分页参数
            queryBuilder.withPageable(PageRequest.of(Integer.parseInt(searchMap.get("page")),
                    Integer.parseInt(searchMap.get("pageSize"))));

            /*
             * 分组查询分类集合
             * addAggregation 添加一个聚合操作
             * terms    相当于sql的 as 取别名
             * field    表示根据哪一个域进行分组
             */
            queryBuilder.addAggregation(AggregationBuilders.terms("skuCategory").field("categoryName"));
        }



        /*
         *  使用 ElasticsearchTemplate 执行搜索
         *  搜索条件封装的对象
         *  搜索的结果集（集合数据）需要转换的类型
         *  AggregatedPage<SkuInfo> 搜索结果集的封装
         */
        AggregatedPage<SkuInfo> page = elasticsearchTemplate.queryForPage(queryBuilder.build(), SkuInfo.class);

        // 分页参数总条数
        long totalElements = page.getTotalElements();
        // 总页数
        int totalPages = page.getTotalPages();
        // 结果集
        List<SkuInfo> content = page.getContent();

        /*
         * 获取分组数据
         * page.getAggregations()       获取的是集合 可以根据多个域进行分组
         * .get("skuCategory")          获取指定域 也就是某一个字段的集合数据  [“手机”，“家电”]
         */
        StringTerms stringTerms = page.getAggregations().get("skuCategory");

        List<String> categoryNameList = new ArrayList<>();
        for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
            // 其中的某一个数据     "手机"
            String categoryName = bucket.getKeyAsString();
            categoryNameList.add(categoryName);
        }

        // 封装一个Map存储
        Map<String, Object> resultMap = new HashMap<>(16);
        resultMap.put("row",content);
        resultMap.put("total",totalElements);
        resultMap.put("totalPages",totalPages);
        resultMap.put("categoryList",categoryNameList);

        return new Result<>(true,StatusCode.OK,"查询成功",resultMap);
    }
}
