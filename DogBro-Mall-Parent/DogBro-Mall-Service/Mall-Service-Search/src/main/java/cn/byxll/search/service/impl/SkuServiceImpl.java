package cn.byxll.search.service.impl;

import cn.byxll.search.dao.SkuEsMapper;
import cn.byxll.goods.feign.SkuFeign;
import cn.byxll.goods.pojo.Sku;
import cn.byxll.search.service.SkuService;
import cn.byxll.search.SkuInfo;
import com.alibaba.fastjson.JSON;
import entity.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SkuService 接口实现类
 * @author By-Lin
 */
@Service
public class SkuServiceImpl implements SkuService {
    private final SkuEsMapper skuEsMapper;
    private final SkuFeign skuFeign;

    public SkuServiceImpl(SkuEsMapper skuEsMapper, SkuFeign skuFeign) {
        this.skuEsMapper = skuEsMapper;
        this.skuFeign = skuFeign;
    }

    /**
     * 导入sku数据
     */
    @Override
    public void importSkuData() {
        // 调用feign 查询 sku 集合
        Result<List<Sku>> skuResult = skuFeign.findAll();

        // 将List<Sku>转成List<SkuInfo>
        List<SkuInfo> skuInfoList = JSON.parseArray(JSON.toJSONString(skuResult.getData()), SkuInfo.class);

        // 调用Dao实现数据的批量导入
        skuEsMapper.saveAll(skuInfoList);
    }
}
