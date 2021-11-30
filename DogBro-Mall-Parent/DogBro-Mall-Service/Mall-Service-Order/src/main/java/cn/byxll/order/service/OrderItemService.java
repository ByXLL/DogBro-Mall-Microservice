package cn.byxll.order.service;

import cn.byxll.order.pojo.OrderItem;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * OrderItem业务层接口类
 * @author @By-Lin
 */
public interface OrderItemService {

    /**
     * 新增OrderItem
     * @param   orderItem      OrderItem实体
     * @return                 响应数据
     */
    Result<Boolean> add(OrderItem orderItem);

    /**
     * 通过组件删除OrderItem
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(String id);

    /**
     * 修改OrderItem数据
     * @param orderItem      OrderItem实体
     * @return              响应数据
     */
    Result<Boolean> update(OrderItem orderItem);

    /**
     * 根据ID查询OrderItem
     * @param id        主键id
     * @return          响应数据
     */
    Result<OrderItem> findById(String id);

    /**
     * 查询所有OrderItem
     * @return          响应数据
     */
    Result<List<OrderItem>> findAll();

    /**
     * OrderItem分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<OrderItem>> findByPager(Integer page, Integer pageSize);

    /**
     * OrderItem条件分页查询
     * @param orderItem      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<OrderItem>> findPagerByParam(OrderItem orderItem, Integer page, Integer pageSize);
    

    /**
     * OrderItem多条件搜索方法
     * @param orderItem      条件实体
     * @return              响应数据
     */
    Result<List<OrderItem>> findList(OrderItem orderItem);
}
