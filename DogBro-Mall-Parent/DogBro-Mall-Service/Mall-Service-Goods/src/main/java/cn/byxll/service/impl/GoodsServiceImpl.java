package cn.byxll.service.impl;

import cn.byxll.dao.BrandMapper;
import cn.byxll.dao.CategoryMapper;
import cn.byxll.dao.SkuMapper;
import cn.byxll.dao.SpuMapper;
import cn.byxll.goods.dto.Goods;
import cn.byxll.goods.pojo.Brand;
import cn.byxll.goods.pojo.Category;
import cn.byxll.goods.pojo.Sku;
import cn.byxll.goods.pojo.Spu;
import cn.byxll.service.GoodsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entity.IdWorker;
import entity.Result;
import entity.StatusCode;
import exception.OperationalException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * 添加商品 service 接口实现类
 * @author By-Lin
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    private final SpuMapper spuMapper;
    private final SkuMapper skuMapper;
    private final CategoryMapper categoryMapper;
    private final BrandMapper brandMapper;
    private IdWorker idWorker;

    public GoodsServiceImpl(SpuMapper spuMapper, SkuMapper skuMapper, CategoryMapper categoryMapper, BrandMapper brandMapper) {
        this.spuMapper = spuMapper;
        this.skuMapper = skuMapper;
        this.categoryMapper = categoryMapper;
        this.brandMapper = brandMapper;
    }

    /**
     * 添加商品
     * @param goods 商品dto
     * @return 响应数据
     */
    @Override
    @Transactional(rollbackFor = OperationalException.class)
    public Result<Boolean> add(Goods goods) {
        if(goods == null || goods.getSpu() == null || goods.getSkuList().size() < 1) {
            throw new IllegalArgumentException("商品信息异常");
        }
        // 初始化spu信息
        Spu spu = goods.getSpu();
        spu.setId(idWorker.nextId());
        spu.setSaleNum(0);
        spu.setCommentNum(0);
        spu.setIsMarketable("1");
        spu.setIsDelete("0");
        spu.setStatus("0");
        spuMapper.insertSelective(spu);

        // 查询分类信息
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());
        if(category == null) { throw new OperationalException("添加失败,商品分类不存在"); }

        // 查询品牌信息
        Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
        if(brand == null) { throw new OperationalException("添加失败,品牌不存在"); }

        // 轮询插入sku
        for (Sku sku : goods.getSkuList()) {
            // 组装sku name
            StringBuilder skuName = new StringBuilder(spu.getName());

            // 获取sku中的spec字段 {'颜色': '黑色', '版本': '10GB+256GB'}
            // 将spec转成map
            if(StringUtils.isEmpty(sku.getSpec())) { sku.setSpec("{}"); }
            Map<String,String> specMap = JSON.parseObject(sku.getSpec(), Map.class);
            for (Map.Entry<String, String> entry : specMap.entrySet()) { skuName.append(entry.getValue()); }

            // 初始化sku信息
            sku.setId(idWorker.nextId());
            sku.setName(String.valueOf(skuName));
            sku.setCreateTime(new Date());
            sku.setUpdateTime(new Date());
            sku.setSpuId(spu.getId());
            sku.setCategoryId(spu.getCategory3Id());
            sku.setCategoryName(category.getName());
            sku.setBrandName(brand.getName());
            sku.setSaleNum(0);
            sku.setCommentNum(0);
            sku.setStatus("1");

            skuMapper.insert(sku);
        }
        return new Result<>(true, StatusCode.OK,"商品添加成功");
    }
}
