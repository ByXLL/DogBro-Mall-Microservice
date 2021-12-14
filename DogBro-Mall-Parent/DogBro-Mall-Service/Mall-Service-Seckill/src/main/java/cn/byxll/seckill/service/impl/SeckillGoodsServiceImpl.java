package cn.byxll.seckill.service.impl;

import cn.byxll.seckill.dao.SeckillGoodsMapper;
import cn.byxll.seckill.pojo.SeckillGoods;
import cn.byxll.seckill.service.SeckillGoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * SeckillGoods业务层接口实现类
 * @author By-Lin
 */
@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    private final SeckillGoodsMapper seckillGoodsMapper;

    public SeckillGoodsServiceImpl(SeckillGoodsMapper seckillGoodsMapper) {
        this.seckillGoodsMapper = seckillGoodsMapper;
    }

    /**
     * 新增SeckillGoods
     * @param seckillGoods      SeckillGoods实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(SeckillGoods seckillGoods){
        if(seckillGoods == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = seckillGoodsMapper.insert(seckillGoods);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(Long id){
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = seckillGoodsMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改SeckillGoods
     * @param seckillGoods      SeckillGoods实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(SeckillGoods seckillGoods){
        if(seckillGoods == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = seckillGoodsMapper.updateByPrimaryKey(seckillGoods);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询SeckillGoods
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<SeckillGoods> findById(Long id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        SeckillGoods seckillGoods = seckillGoodsMapper.selectByPrimaryKey(id);
        if(seckillGoods != null) { return new Result<>(true, StatusCode.OK, "查询成功", seckillGoods); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有SeckillGoods
     * @return      响应数据
     */
    @Override
    public Result<List<SeckillGoods>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", seckillGoodsMapper.selectAll());
    }

    /**
     * SeckillGoods分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<SeckillGoods>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(seckillGoodsMapper.selectAll()));
    }


    /**
     * SeckillGoods条件分页查询
     * @param seckillGoods      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<SeckillGoods>> findPagerByParam(SeckillGoods seckillGoods, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(seckillGoods);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<SeckillGoods>(seckillGoodsMapper.selectByExample(example)));
    }

    /**
     * SeckillGoods多条件搜索方法
     * @param seckillGoods      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<SeckillGoods>> findList(SeckillGoods seckillGoods){
        if(seckillGoods == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(seckillGoods);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<SeckillGoods>(seckillGoodsMapper.selectByExample(example)));
    }


    /**
     * SeckillGoods构建查询对象
     * @param seckillGoods      SeckillGoods实体
     * @return              查询对象
     */
    private Example createExample(SeckillGoods seckillGoods){
        Example example = new Example(SeckillGoods.class);
        Example.Criteria criteria = example.createCriteria();
        if(seckillGoods!=null){
            // 
            if(!StringUtils.isEmpty(seckillGoods.getId())){
                criteria.andEqualTo("id",seckillGoods.getId());
            }
            // spu ID
            if(!StringUtils.isEmpty(seckillGoods.getGoodsId())){
                criteria.andEqualTo("goodsId",seckillGoods.getGoodsId());
            }
            // sku ID
            if(!StringUtils.isEmpty(seckillGoods.getItemId())){
                criteria.andEqualTo("itemId",seckillGoods.getItemId());
            }
            // 标题
            if(!StringUtils.isEmpty(seckillGoods.getTitle())){
                criteria.andLike("title","%"+seckillGoods.getTitle()+"%");
            }
            // 商品图片
            if(!StringUtils.isEmpty(seckillGoods.getSmallPic())){
                criteria.andEqualTo("smallPic",seckillGoods.getSmallPic());
            }
            // 原价格
            if(!StringUtils.isEmpty(seckillGoods.getPrice())){
                criteria.andEqualTo("price",seckillGoods.getPrice());
            }
            // 秒杀价格
            if(!StringUtils.isEmpty(seckillGoods.getCostPrice())){
                criteria.andEqualTo("costPrice",seckillGoods.getCostPrice());
            }
            // 商家ID
            if(!StringUtils.isEmpty(seckillGoods.getSellerId())){
                criteria.andEqualTo("sellerId",seckillGoods.getSellerId());
            }
            // 添加日期
            if(!StringUtils.isEmpty(seckillGoods.getCreateTime())){
                criteria.andEqualTo("createTime",seckillGoods.getCreateTime());
            }
            // 审核日期
            if(!StringUtils.isEmpty(seckillGoods.getCheckTime())){
                criteria.andEqualTo("checkTime",seckillGoods.getCheckTime());
            }
            // 审核状态，0未审核，1审核通过，2审核不通过
            if(!StringUtils.isEmpty(seckillGoods.getStatus())){
                criteria.andEqualTo("status",seckillGoods.getStatus());
            }
            // 开始时间
            if(!StringUtils.isEmpty(seckillGoods.getStartTime())){
                criteria.andEqualTo("startTime",seckillGoods.getStartTime());
            }
            // 结束时间
            if(!StringUtils.isEmpty(seckillGoods.getEndTime())){
                criteria.andEqualTo("endTime",seckillGoods.getEndTime());
            }
            // 秒杀商品数
            if(!StringUtils.isEmpty(seckillGoods.getNum())){
                criteria.andEqualTo("num",seckillGoods.getNum());
            }
            // 剩余库存数
            if(!StringUtils.isEmpty(seckillGoods.getStockCount())){
                criteria.andEqualTo("stockCount",seckillGoods.getStockCount());
            }
            // 描述
            if(!StringUtils.isEmpty(seckillGoods.getIntroduction())){
                criteria.andEqualTo("introduction",seckillGoods.getIntroduction());
            }
        }
        return example;
    }

}
