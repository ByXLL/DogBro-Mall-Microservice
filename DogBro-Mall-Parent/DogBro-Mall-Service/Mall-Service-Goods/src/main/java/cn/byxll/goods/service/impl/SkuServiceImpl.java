package cn.byxll.goods.service.impl;

import cn.byxll.goods.dao.SkuMapper;
import cn.byxll.goods.pojo.Sku;
import cn.byxll.goods.service.SkuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import exception.OperationalException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
import java.util.Map;

/**
 * Sku Service接口实现类
 * @Author: By-Lin
 */
@Service
public class SkuServiceImpl implements SkuService {
    private final SkuMapper skuMapper;

    public SkuServiceImpl(SkuMapper skuMapper) {
        this.skuMapper = skuMapper;
    }

    /**
     * 增加Sku
     * @param sku      Sku 实体
     * @return         响应数据
     */
    @Override
    public Result<Boolean> add(Sku sku){
        if(sku == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = skuMapper.insert(sku);
        if(i>0) { return new Result<>(true, StatusCode.OK, "添加成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id      Sku 主键id
     * @return        响应数据
     */
    @Override
    public Result<Boolean> delete(String id){
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = skuMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "删除成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 减库存
     * @param decrMap 减库存信息
     * @return        响应数据
     */
    @Override
    public Result<Boolean> decrCount(Map<Long,Integer> decrMap){
        if(decrMap == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        for (Map.Entry<Long, Integer> entry : decrMap.entrySet()) {
            Long skuId = entry.getKey();
            Integer num = entry.getValue();
            int i = skuMapper.decrCount(skuId,num);
            if(i<1) { throw new OperationalException("扣减库存失败"); }
        }
        return new Result<>(true, StatusCode.OK, "操作成功");
    }

    /**
     * 修改Sku
     * @param sku       Sku实体
     * @return          响应数据
     */
    @Override
    public Result<Boolean> update(Sku sku){
        if(sku == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = skuMapper.updateByPrimaryKey(sku);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询Sku
     * @param id        Sku 主键
     * @return          响应数据
     */
    @Override
    public Result<Sku> findById(String id){
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        Sku sku = skuMapper.selectByPrimaryKey(id);
        if(sku == null) { return new Result<>(false, StatusCode.ERROR, "查询失败",null); }
        return new Result<>(true, StatusCode.OK, "查询成功", sku);
    }

    /**
     * Sku分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Sku>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(skuMapper.selectAll()));
    }

    /**
     * Sku条件+分页查询
     * @param sku      查询条件
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Sku>> findPagerByParam(Sku sku, Integer page, Integer pageSize){
        if(sku == null || page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        // 静态分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(sku);
        //分页
        PageHelper.startPage(page,pageSize);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "查询成功",  new PageInfo<>(skuMapper.selectByExample(example)));
    }

    /**
     * Sku条件查询
     * @param sku      Sku实体
     * @return         响应数据
     */
    @Override
    public Result<List<Sku>> findListByParam(Sku sku){
        if(sku == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //构建查询条件
        Example example = createExample(sku);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "查询成功", skuMapper.selectByExample(example));
    }

    /**
     * 查询Sku全部数据
     * @return      响应数据
     */
    @Override
    public Result<List<Sku>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", skuMapper.selectAll());
    }

    /**
     * Sku构建查询对象
     * @param sku      sku实体
     * @return              Example实体
     */
    public Example createExample(Sku sku){
        Example example=new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();
        if(sku!=null){
            // 商品id
            if(!StringUtils.isEmpty(String.valueOf(sku.getId()))){
                criteria.andEqualTo("id",sku.getId());
            }
            // 商品条码
            if(!StringUtils.isEmpty(sku.getSn())){
                criteria.andEqualTo("sn",sku.getSn());
            }
            // SKU名称
            if(!StringUtils.isEmpty(sku.getName())){
                criteria.andLike("name","%"+sku.getName()+"%");
            }
            // 价格（分）
            if(!StringUtils.isEmpty(String.valueOf(sku.getPrice()))){
                criteria.andEqualTo("price",sku.getPrice());
            }
            // 库存数量
            if(!StringUtils.isEmpty(String.valueOf(sku.getNum()))){
                criteria.andEqualTo("num",sku.getNum());
            }
            // 库存预警数量
            if(!StringUtils.isEmpty(String.valueOf(sku.getAlertNum()))){
                criteria.andEqualTo("alertNum",sku.getAlertNum());
            }
            // 商品图片
            if(!StringUtils.isEmpty(sku.getImage())){
                criteria.andEqualTo("image",sku.getImage());
            }
            // 商品图片列表
            if(!StringUtils.isEmpty(sku.getImages())){
                criteria.andEqualTo("images",sku.getImages());
            }
            // 重量（克）
            if(!StringUtils.isEmpty(String.valueOf(sku.getWeight()))){
                criteria.andEqualTo("weight",sku.getWeight());
            }
            // 创建时间
            if(!StringUtils.isEmpty(String.valueOf(sku.getCreateTime()))){
                criteria.andEqualTo("createTime",sku.getCreateTime());
            }
            // 更新时间
            if(!StringUtils.isEmpty(String.valueOf(sku.getUpdateTime()))){
                criteria.andEqualTo("updateTime",sku.getUpdateTime());
            }
            // SPUID
            if(!StringUtils.isEmpty(String.valueOf(sku.getSpuId()))){
                criteria.andEqualTo("spuId",sku.getSpuId());
            }
            // 类目ID
            if(!StringUtils.isEmpty(String.valueOf(sku.getCategoryId()))){
                criteria.andEqualTo("categoryId",sku.getCategoryId());
            }
            // 类目名称
            if(!StringUtils.isEmpty(sku.getCategoryName())){
                criteria.andEqualTo("categoryName",sku.getCategoryName());
            }
            // 品牌名称
            if(!StringUtils.isEmpty(sku.getBrandName())){
                criteria.andEqualTo("brandName",sku.getBrandName());
            }
            // 规格
            if(!StringUtils.isEmpty(sku.getSpec())){
                criteria.andEqualTo("spec",sku.getSpec());
            }
            // 销量
            if(!StringUtils.isEmpty(String.valueOf(sku.getSaleNum()))){
                criteria.andEqualTo("saleNum",sku.getSaleNum());
            }
            // 评论数
            if(!StringUtils.isEmpty(String.valueOf(sku.getCommentNum()))){
               criteria.andEqualTo("commentNum",sku.getCommentNum());
            }
            // 商品状态 1-正常，2-下架，3-删除
            if(!StringUtils.isEmpty(sku.getStatus())){
               criteria.andEqualTo("status",sku.getStatus());
            }
        }
        return example;
    }
}
