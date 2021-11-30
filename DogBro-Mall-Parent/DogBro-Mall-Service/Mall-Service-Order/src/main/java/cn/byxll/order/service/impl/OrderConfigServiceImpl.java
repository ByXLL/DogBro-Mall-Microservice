package cn.byxll.order.service.impl;

import cn.byxll.order.dao.OrderConfigMapper;
import cn.byxll.order.pojo.OrderConfig;
import cn.byxll.order.service.OrderConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * OrderConfig业务层接口实现类
 * @author By-Lin
 */
@Service
public class OrderConfigServiceImpl implements OrderConfigService {

    private final OrderConfigMapper orderConfigMapper;

    public OrderConfigServiceImpl(OrderConfigMapper orderConfigMapper) {
        this.orderConfigMapper = orderConfigMapper;
    }

    /**
     * 新增OrderConfig
     * @param orderConfig      OrderConfig实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(OrderConfig orderConfig){
        if(orderConfig == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = orderConfigMapper.insert(orderConfig);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(Integer id){
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = orderConfigMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改OrderConfig
     * @param orderConfig      OrderConfig实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(OrderConfig orderConfig){
        if(orderConfig == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = orderConfigMapper.updateByPrimaryKey(orderConfig);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询OrderConfig
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<OrderConfig> findById(Integer id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        OrderConfig orderConfig = orderConfigMapper.selectByPrimaryKey(id);
        if(orderConfig != null) { return new Result<>(true, StatusCode.OK, "查询成功", orderConfig); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有OrderConfig
     * @return      响应数据
     */
    @Override
    public Result<List<OrderConfig>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", orderConfigMapper.selectAll());
    }

    /**
     * OrderConfig分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<OrderConfig>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(orderConfigMapper.selectAll()));
    }


    /**
     * OrderConfig条件分页查询
     * @param orderConfig      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<OrderConfig>> findPagerByParam(OrderConfig orderConfig, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(orderConfig);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<OrderConfig>(orderConfigMapper.selectByExample(example)));
    }

    /**
     * OrderConfig多条件搜索方法
     * @param orderConfig      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<OrderConfig>> findList(OrderConfig orderConfig){
        if(orderConfig == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(orderConfig);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<OrderConfig>(orderConfigMapper.selectByExample(example)));
    }


    /**
     * OrderConfig构建查询对象
     * @param orderConfig      OrderConfig实体
     * @return              查询对象
     */
    private Example createExample(OrderConfig orderConfig){
        Example example = new Example(OrderConfig.class);
        Example.Criteria criteria = example.createCriteria();
        if(orderConfig!=null){
            // ID
            if(!StringUtils.isEmpty(orderConfig.getId())){
                criteria.andEqualTo("id",orderConfig.getId());
            }
            // 正常订单超时时间（分）
            if(!StringUtils.isEmpty(orderConfig.getOrderTimeout())){
                criteria.andEqualTo("orderTimeout",orderConfig.getOrderTimeout());
            }
            // 秒杀订单超时时间（分）
            if(!StringUtils.isEmpty(orderConfig.getSeckillTimeout())){
                criteria.andEqualTo("seckillTimeout",orderConfig.getSeckillTimeout());
            }
            // 自动收货（天）
            if(!StringUtils.isEmpty(orderConfig.getTakeTimeout())){
                criteria.andEqualTo("takeTimeout",orderConfig.getTakeTimeout());
            }
            // 售后期限
            if(!StringUtils.isEmpty(orderConfig.getServiceTimeout())){
                criteria.andEqualTo("serviceTimeout",orderConfig.getServiceTimeout());
            }
            // 自动五星好评
            if(!StringUtils.isEmpty(orderConfig.getCommentTimeout())){
                criteria.andEqualTo("commentTimeout",orderConfig.getCommentTimeout());
            }
        }
        return example;
    }

}
