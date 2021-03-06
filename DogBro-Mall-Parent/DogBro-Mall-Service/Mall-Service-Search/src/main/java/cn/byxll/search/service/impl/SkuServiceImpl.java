package cn.byxll.search.service.impl;

import cn.byxll.goods.dto.SearchParam;
import cn.byxll.goods.vo.GoodsListVO;
import cn.byxll.search.dao.SkuEsMapper;
import cn.byxll.goods.feign.SkuFeign;
import cn.byxll.goods.pojo.Sku;
import cn.byxll.search.service.SkuService;
import cn.byxll.search.pojo.SkuInfo;
import com.alibaba.fastjson.JSON;
import entity.Result;
import entity.StatusCode;
import exception.OperationalException;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public Result<GoodsListVO> search(SearchParam searchMap) {
        // 构建搜索对象
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        /*
         * 添加 分类分组查询条件
         * addAggregation 添加一个聚合操作
         * terms    相当于sql的 as 取别名
         * field    表示根据哪一个域进行分组
         */
        queryBuilder.addAggregation(AggregationBuilders.terms("skuCategory").field("categoryName"));

        /*
         * 添加 商品品牌分类查询条件
         */
        queryBuilder.addAggregation(AggregationBuilders.terms("skuBrand").field("brandName"));

        /*
         * 添加 商品规格名分组查询条件
         * 获取商品规格名
         */
        queryBuilder.addAggregation(AggregationBuilders.terms("skuSpec").field("spec.keyword"));

        if(searchMap != null) {
            // 根据关键词搜索
            if(StringUtils.isNotBlank(searchMap.getKeywords())) {
                // field 对应的是es 数据库列名
//                queryBuilder.withQuery(QueryBuilders.queryStringQuery(searchMap.getKeywords()).field("name"));
                // 多字段分词搜索
                queryBuilder.withQuery(QueryBuilders.multiMatchQuery(searchMap.getKeywords(),"name","categoryName","brandName"));
            }

            Integer pageIndex = searchMap.getPage() == null ? 0 : Math.max((searchMap.getPage() - 1), 0);
            Integer pageSize = searchMap.getPageSize() == null ? 20 : searchMap.getPageSize();
            // 分页参数
            queryBuilder.withPageable(PageRequest.of(pageIndex,pageSize));
            // 价格区间搜索
            if(searchMap.getMinPrice() != null) {
                // 大于
                queryBuilder.withFilter(QueryBuilders.rangeQuery("price").gt(searchMap.getMinPrice()));
            }
            if(searchMap.getMaxPrice() != null) {
                // 小于
                queryBuilder.withFilter(QueryBuilders.rangeQuery("price").lte(searchMap.getMaxPrice()));
            }
            // 分类名称
            if(StringUtils.isNotBlank(searchMap.getCategory())) {
                queryBuilder.withFilter(QueryBuilders.termQuery("categoryName",searchMap.getCategory()));
            }
            // 品牌
            if(StringUtils.isNotBlank(searchMap.getBrand())) {
                queryBuilder.withFilter(QueryBuilders.termQuery("brandName",searchMap.getBrand()));
            }

            if(StringUtils.isNotBlank(searchMap.getSpecs())){
                //{spec:"网络:电信4G,颜色:红色"}
                String[] specStrArr = searchMap.getSpecs().split(",");
                for (String item : specStrArr) {
                    String[] strings = item.split(":");
                    // 截取规格名称
                    queryBuilder.withFilter(QueryBuilders.termQuery("specMap."+strings[0] +".keyword", strings[1]));

                }
            }

            /* 获取排序 */
            String sortField = searchMap.getSortField();
            String sortRule = searchMap.getSortRule();
            if(StringUtils.isNotBlank(sortField) && StringUtils.isNotBlank(sortRule)) {
                // 自动通过回传的排序条件
                queryBuilder.withSort(new FieldSortBuilder(sortField).order(SortOrder.valueOf(sortRule)));
            }
        }

        /*
         *  使用 ElasticsearchTemplate 执行搜索
         *  搜索条件封装的对象
         *  搜索的结果集（集合数据）需要转换的类型
         *  AggregatedPage<SkuInfo> 搜索结果集的封装
         */
        AggregatedPage<SkuInfo> skuInfos = elasticsearchTemplate.queryForPage(queryBuilder.build(), SkuInfo.class);

        // 分页参数总条数
        long totalElements = skuInfos.getTotalElements();
        // 总页数
        int totalPages = skuInfos.getTotalPages();
        // 结果集
        List<SkuInfo> content = skuInfos.getContent();


        /*
         * 获取分类数据
         * page.getAggregations()       获取的是集合 可以根据多个域进行分组
         * .get("skuCategory")          获取指定域 也就是某一个字段的集合数据  [“手机”，“家电”]
         */
        StringTerms skuCategoryStrT = skuInfos.getAggregations().get("skuCategory");
        List<String> categoryList = getStringsCategoryList(skuCategoryStrT);


        /*
         * 获取品牌数据
         * [“华为”，“小米”]
         */
        StringTerms skuBrandStrT = skuInfos.getAggregations().get("skuBrand");
        List<String> brandList = getStringsBrandList(skuBrandStrT);

        /*
         * 获取规格数据
         * [{"颜色":"红色","内存":"128G"}，{"颜色":"红色","内存":"256G"}]
         */
        StringTerms skuSpecStrT = skuInfos.getAggregations().get("skuSpec");
        Map<String, Set<String>> specList = getStringsSpecList(skuSpecStrT);


        // 获取分页信息
        Pageable pageable = skuInfos.getPageable();
        Integer pageNum = pageable.getPageNumber();
        Integer pageSize = pageable.getPageSize();

        // 封装回传数据
        GoodsListVO goodsList = new GoodsListVO();
        goodsList.setRows(content);
        goodsList.setTotal(totalElements);
        goodsList.setTotalPages(totalPages);
        goodsList.setPageNum(pageNum);
        goodsList.setPageSize(pageSize);
        goodsList.setCategoryList(categoryList);
        goodsList.setBrandList(brandList);
        goodsList.setSpecList(specList);

        return new Result<>(true,StatusCode.OK,"查询成功",goodsList);
    }

    /**
     * 将 商品分类数据 StringTerms 转换成字符串数组
     * @param stringTerms       商品分类聚合结果
     * @return                  分类字符串数组
     */
    private List<String> getStringsCategoryList(StringTerms stringTerms) {
        List<String> categoryNameList = new ArrayList<>();
        for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
            // 其中的某一个数据     "手机"
            String categoryName = bucket.getKeyAsString();
            categoryNameList.add(categoryName);
        }
        return categoryNameList;
    }


    /**
     * 将 品牌数据 StringTerms 转换成字符串数组
     * @param stringTerms       品牌聚合结果
     * @return                  品牌字符串数组
     */
    private List<String> getStringsBrandList(StringTerms stringTerms) {
        List<String> brandNameList = new ArrayList<>();
        for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
            // 其中的某一个数据     "华为"、"苹果"
            String brandName = bucket.getKeyAsString();
            brandNameList.add(brandName);
        }
        return brandNameList;
    }

    /**
     * 将 规格数据 StringTerms 转换成字符串数组
     * @param stringTerms       规格聚合结果
     * @return                  规格map对象
     */
    private Map<String, Set<String>> getStringsSpecList(StringTerms stringTerms) {
        /*
         * 当前 stringTerms 格式为以下的json数据
         * [{"颜色":"红色","内存":"128G"}，{"颜色":"红色","内存":"256G"}]
         */
        List<String> specList = new ArrayList<>();
        for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
            String specName = bucket.getKeyAsString();
            specList.add(specName);
        }
        // 合并后的map对象
        Map<String, Set<String>> allSpec = new HashMap<>();
        // 1.循环specList
        for (String spec : specList) {
            /*
             * 2、将每一个Json字符串转换成Map
             * {"颜色":"红色","内存":"128G"}
             */
            Map<String,String> itemMap = JSON.parseObject(spec,Map.class);

            // 3、循环所有的map 将每一个Map对象合并成一个Map<String,Set<String>>
            for (Map.Entry<String, String> entry : itemMap.entrySet()) {
                /*
                 * 4、取出当前Map，并获取对应的Key，以及对应的value
                 * 颜色
                 */
                String key = entry.getKey();
                // 规格值 红色
                String value = entry.getValue();

                /*
                 * 将当前循环的数据合并到一个Map<String, Set<String>中
                 * 需要先获取当前规格对应的set集合数据
                 * 否则将会替换原来的存入的值，变成了替换而不是插入
                 * {"颜色":["红色"，"银色"]}
                 */
                Set<String> specSet = allSpec.get(key);
                if(specSet == null) {
                    specSet = new HashSet<>();
                }
                // 红色
                specSet.add(value);
                // {"颜色":["红色"，"银色"]}
                allSpec.put(key,specSet);
            }
        }

        return allSpec;
    }
}

