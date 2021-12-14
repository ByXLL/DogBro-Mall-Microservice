package cn.byxll.seckill.service.impl;

import cn.byxll.seckill.dao.SeckillOrderMapper;
import cn.byxll.seckill.pojo.SeckillOrder;
import cn.byxll.seckill.service.SeckillOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * SeckillOrder业务层接口实现类
 * @author By-Lin
 */
@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

    private final SeckillOrderMapper seckillOrderMapper;

    public SeckillOrderServiceImpl(SeckillOrderMapper seckillOrderMapper) {
        this.seckillOrderMapper = seckillOrderMapper;
    }

    /**
     * 新增SeckillOrder
     * @param seckillOrder      SeckillOrder实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(SeckillOrder seckillOrder){
        if(seckillOrder == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = seckillOrderMapper.insert(seckillOrder);
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
        int i = seckillOrderMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改SeckillOrder
     * @param seckillOrder      SeckillOrder实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(SeckillOrder seckillOrder){
        if(seckillOrder == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = seckillOrderMapper.updateByPrimaryKey(seckillOrder);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询SeckillOrder
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<SeckillOrder> findById(Long id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        SeckillOrder seckillOrder = seckillOrderMapper.selectByPrimaryKey(id);
        if(seckillOrder != null) { return new Result<>(true, StatusCode.OK, "查询成功", seckillOrder); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有SeckillOrder
     * @return      响应数据
     */
    @Override
    public Result<List<SeckillOrder>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", seckillOrderMapper.selectAll());
    }

    /**
     * SeckillOrder分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<SeckillOrder>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(seckillOrderMapper.selectAll()));
    }


    /**
     * SeckillOrder条件分页查询
     * @param seckillOrder      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<SeckillOrder>> findPagerByParam(SeckillOrder seckillOrder, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(seckillOrder);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<SeckillOrder>(seckillOrderMapper.selectByExample(example)));
    }

    /**
     * SeckillOrder多条件搜索方法
     * @param seckillOrder      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<SeckillOrder>> findList(SeckillOrder seckillOrder){
        if(seckillOrder == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(seckillOrder);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<SeckillOrder>(seckillOrderMapper.selectByExample(example)));
    }


    /**
     * SeckillOrder构建查询对象
     * @param seckillOrder      SeckillOrder实体
     * @return              查询对象
     */
    private Example createExample(SeckillOrder seckillOrder){
        Example example = new Example(SeckillOrder.class);
        Example.Criteria criteria = example.createCriteria();
        if(seckillOrder!=null){
            // 主键
            if(!StringUtils.isEmpty(seckillOrder.getId())){
                criteria.andEqualTo("id",seckillOrder.getId());
            }
            // 秒杀商品ID
            if(!StringUtils.isEmpty(seckillOrder.getSeckillId())){
                criteria.andEqualTo("seckillId",seckillOrder.getSeckillId());
            }
            // 支付金额
            if(!StringUtils.isEmpty(seckillOrder.getMoney())){
                criteria.andEqualTo("money",seckillOrder.getMoney());
            }
            // 用户
            if(!StringUtils.isEmpty(seckillOrder.getUserId())){
                criteria.andEqualTo("userId",seckillOrder.getUserId());
            }
            // 商家
            if(!StringUtils.isEmpty(seckillOrder.getSellerId())){
                criteria.andEqualTo("sellerId",seckillOrder.getSellerId());
            }
            // 创建时间
            if(!StringUtils.isEmpty(seckillOrder.getCreateTime())){
                criteria.andEqualTo("createTime",seckillOrder.getCreateTime());
            }
            // 支付时间
            if(!StringUtils.isEmpty(seckillOrder.getPayTime())){
                criteria.andEqualTo("payTime",seckillOrder.getPayTime());
            }
            // 状态，0未支付，1已支付
            if(!StringUtils.isEmpty(seckillOrder.getStatus())){
                criteria.andEqualTo("status",seckillOrder.getStatus());
            }
            // 收货人地址
            if(!StringUtils.isEmpty(seckillOrder.getReceiverAddress())){
                criteria.andEqualTo("receiverAddress",seckillOrder.getReceiverAddress());
            }
            // 收货人电话
            if(!StringUtils.isEmpty(seckillOrder.getReceiverMobile())){
                criteria.andEqualTo("receiverMobile",seckillOrder.getReceiverMobile());
            }
            // 收货人
            if(!StringUtils.isEmpty(seckillOrder.getReceiver())){
                criteria.andEqualTo("receiver",seckillOrder.getReceiver());
            }
            // 交易流水
            if(!StringUtils.isEmpty(seckillOrder.getTransactionId())){
                criteria.andEqualTo("transactionId",seckillOrder.getTransactionId());
            }
        }
        return example;
    }

}
