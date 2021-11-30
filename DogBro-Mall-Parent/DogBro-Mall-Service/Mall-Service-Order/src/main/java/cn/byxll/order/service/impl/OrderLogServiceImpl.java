package cn.byxll.order.service.impl;

import cn.byxll.order.dao.OrderLogMapper;
import cn.byxll.order.pojo.OrderLog;
import cn.byxll.order.service.OrderLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * OrderLog业务层接口实现类
 * @author By-Lin
 */
@Service
public class OrderLogServiceImpl implements OrderLogService {

    private final OrderLogMapper orderLogMapper;

    public OrderLogServiceImpl(OrderLogMapper orderLogMapper) {
        this.orderLogMapper = orderLogMapper;
    }

    /**
     * 新增OrderLog
     * @param orderLog      OrderLog实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(OrderLog orderLog){
        if(orderLog == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = orderLogMapper.insert(orderLog);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(String id){
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = orderLogMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改OrderLog
     * @param orderLog      OrderLog实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(OrderLog orderLog){
        if(orderLog == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = orderLogMapper.updateByPrimaryKey(orderLog);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询OrderLog
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<OrderLog> findById(String id) {
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        OrderLog orderLog = orderLogMapper.selectByPrimaryKey(id);
        if(orderLog != null) { return new Result<>(true, StatusCode.OK, "查询成功", orderLog); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有OrderLog
     * @return      响应数据
     */
    @Override
    public Result<List<OrderLog>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", orderLogMapper.selectAll());
    }

    /**
     * OrderLog分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<OrderLog>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(orderLogMapper.selectAll()));
    }


    /**
     * OrderLog条件分页查询
     * @param orderLog      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<OrderLog>> findPagerByParam(OrderLog orderLog, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(orderLog);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<OrderLog>(orderLogMapper.selectByExample(example)));
    }

    /**
     * OrderLog多条件搜索方法
     * @param orderLog      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<OrderLog>> findList(OrderLog orderLog){
        if(orderLog == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(orderLog);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<OrderLog>(orderLogMapper.selectByExample(example)));
    }


    /**
     * OrderLog构建查询对象
     * @param orderLog      OrderLog实体
     * @return              查询对象
     */
    private Example createExample(OrderLog orderLog){
        Example example = new Example(OrderLog.class);
        Example.Criteria criteria = example.createCriteria();
        if(orderLog!=null){
            // ID
            if(!StringUtils.isEmpty(orderLog.getId())){
                criteria.andEqualTo("id",orderLog.getId());
            }
            // 操作员
            if(!StringUtils.isEmpty(orderLog.getOperater())){
                criteria.andEqualTo("operater",orderLog.getOperater());
            }
            // 操作时间
            if(!StringUtils.isEmpty(orderLog.getOperateTime())){
                criteria.andEqualTo("operateTime",orderLog.getOperateTime());
            }
            // 订单ID
            if(!StringUtils.isEmpty(orderLog.getOrderId())){
                criteria.andEqualTo("orderId",orderLog.getOrderId());
            }
            // 订单状态
            if(!StringUtils.isEmpty(orderLog.getOrderStatus())){
                criteria.andEqualTo("orderStatus",orderLog.getOrderStatus());
            }
            // 付款状态
            if(!StringUtils.isEmpty(orderLog.getPayStatus())){
                criteria.andEqualTo("payStatus",orderLog.getPayStatus());
            }
            // 发货状态
            if(!StringUtils.isEmpty(orderLog.getConsignStatus())){
                criteria.andEqualTo("consignStatus",orderLog.getConsignStatus());
            }
            // 备注
            if(!StringUtils.isEmpty(orderLog.getRemarks())){
                criteria.andEqualTo("remarks",orderLog.getRemarks());
            }
        }
        return example;
    }

}
