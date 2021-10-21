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
import tk.mybatis.mapper.entity.Example;

import java.util.*;

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

    public GoodsServiceImpl(SpuMapper spuMapper, SkuMapper skuMapper, CategoryMapper categoryMapper, BrandMapper brandMapper) {
        this.spuMapper = spuMapper;
        this.skuMapper = skuMapper;
        this.categoryMapper = categoryMapper;
        this.brandMapper = brandMapper;
    }

    /**
     * 保存商品
     * 新增/编辑共用
     * @param goods 商品dto
     * @return      响应数据
     */
    @Override
    @Transactional(rollbackFor = OperationalException.class)
    public Result<Boolean> saveGoods(Goods goods) {
        IdWorker idWorker = new IdWorker();
        if(goods == null || goods.getSpu() == null || goods.getSkuList().size() < 1) {
            throw new IllegalArgumentException("商品信息异常");
        }
        String message = "";
        Spu spu = goods.getSpu();
        if(spu.getId() == null) {
            message = "商品添加成功";
            // 新增
            // 初始化spu信息
            spu.setId(idWorker.nextId());
            spu.setSaleNum(0);
            spu.setCommentNum(0);
            spu.setIsMarketable("1");
            spu.setIsDelete("0");
            spu.setStatus("0");
            spuMapper.insertSelective(spu);
        }else {
            message = "商品修改成功";

            // 修改spu信息
            spuMapper.updateByPrimaryKeySelective(spu);

            // 删除原有的sku
            Sku sku = new Sku();
            sku.setSpuId(spu.getId());
            skuMapper.delete(sku);
        }

        // 查询分类信息
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());
        if(category == null) { throw new OperationalException("操作失败,商品分类不存在"); }

        // 查询品牌信息
        Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
        if(brand == null) { throw new OperationalException("操作失败,品牌不存在"); }

        // 轮询插入sku
        if(goods.getSkuList().size()>0) {
            for (Sku sku : goods.getSkuList()) {
                // 组装sku name  spuName + 规格+规格
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
        }

        return new Result<>(true, StatusCode.OK,message);
    }


    /**
     * 根据spuId 查询商品信息
     * @param spuId     spuId
     * @return          响应数据
     */
    @Override
    public Result<Goods> findBySpuId(Long spuId) {
        if(spuId == null) { return new Result<>(false,StatusCode.ARGERROR,"参数异常",null); }
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        if(spu==null) { return new Result<>(false,StatusCode.ERROR,"商品不存在",null); }

        Sku skuDto = new Sku();
        skuDto.setSpuId(spuId);
        List<Sku> skuList = skuMapper.select(skuDto);

        Goods goods = new Goods();
        goods.setSpu(spu);
        goods.setSkuList(skuList);
        return new Result<>(true, StatusCode.OK,"查询成功",goods);
    }

    /**
     * 根据spuId 审核商品
     * @param spuId spuId
     * @return 响应数据
     */
    @Override
    public Result<Boolean> audit(Long spuId) {
        if(spuId == null) { return new Result<>(false,StatusCode.ARGERROR,"参数异常"); }
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        if(spu == null || "1".equals(spu.getIsDelete())) { return new Result<>(false,StatusCode.ERROR,"操作失败,商品不存在"); }
        spu.setStatus("1");
        spu.setIsMarketable("1");
        int i = spuMapper.updateByPrimaryKeySelective(spu);
        if(i>0) { return new Result<>(true, StatusCode.OK,"操作成功"); }
        return new Result<>(false,StatusCode.ERROR,"操作失败");
    }

    /**
     * 商品下架
     * @param spuId spuId
     * @return      响应数据
     */
    @Override
    public Result<Boolean> pull(Long spuId) {
        if(spuId == null) { return new Result<>(false,StatusCode.ARGERROR,"参数异常"); }
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        if(spu == null || "1".equals(spu.getIsDelete())) { return new Result<>(false,StatusCode.ERROR,"操作失败,商品不存在"); }
        spu.setIsMarketable("0");
        int i = spuMapper.updateByPrimaryKeySelective(spu);
        if(i>0) { return new Result<>(true, StatusCode.OK,"操作成功"); }
        return new Result<>(false,StatusCode.ERROR,"操作失败");
    }

    /**
     * 根据spuId 集合 批量下架商品
     * @param spuIds    spuId集合
     * @return          响应数据
     */
    @Override
    @Transactional(rollbackFor = OperationalException.class)
    public Result<Boolean> pullBySpuIds(List<Long> spuIds) {
        if(spuIds == null || spuIds.size() <1) { return new Result<>(false,StatusCode.ARGERROR,"参数异常"); }

        // 需要满足条件 商品未删除
        Example example = new Example(Spu.class);
        Example.Criteria criteria= example.createCriteria();
        criteria.andIn("id", Arrays.asList(spuIds));
        criteria.andEqualTo("isDelete","0");

        Spu spu = new Spu();
        spu.setIsMarketable("1");
        int i = spuMapper.updateByExampleSelective(spu, example);
        if(i != spuIds.size()) { throw new OperationalException("操作失败");}
        return new Result<>(true, StatusCode.OK,"操作成功");
    }

    /**
     * 根据spuId 商品上架
     * @param spuId         spuId
     * @return               响应数据
     */
    @Override
    public Result<Boolean> put(Long spuId) {
        if(spuId == null) { return new Result<>(false,StatusCode.ARGERROR,"参数异常"); }
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        if(spu == null || "1".equals(spu.getIsDelete())) { return new Result<>(false,StatusCode.ERROR,"操作失败,商品不存在"); }
        if(!"1".equals(spu.getStatus())) { return new Result<>(false,StatusCode.ERROR,"操作失败,未审批商品不能上架"); }
        spu.setIsMarketable("1");
        int i = spuMapper.updateByPrimaryKeySelective(spu);
        if(i>0) { return new Result<>(true, StatusCode.OK,"操作成功"); }
        return new Result<>(false,StatusCode.ERROR,"操作失败");
    }

    /**
     * 根据spuId 集合 批量上架商品
     * @param spuIds    spuId集合
     * @return          响应数据
     */
    @Override
    @Transactional(rollbackFor = OperationalException.class)
    public Result<Boolean> putBySpuIds(List<Long> spuIds) {
        if(spuIds == null || spuIds.size() <1) { return new Result<>(false,StatusCode.ARGERROR,"参数异常"); }
        // 需要满足两个条件 商品未删除、且为已审核
        Example example = new Example(Spu.class);
        Example.Criteria criteria= example.createCriteria();
        criteria.andIn("id", Arrays.asList(spuIds));
        criteria.andEqualTo("isDelete","0");
        criteria.andEqualTo("status","1");

        Spu spu = new Spu();
        spu.setIsMarketable("1");
        int i = spuMapper.updateByExampleSelective(spu, example);
        if(i != spuIds.size()) { throw new OperationalException("操作失败");}
        return new Result<>(true, StatusCode.OK,"操作成功");
    }
}
