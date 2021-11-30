package cn.byxll.order.service;

import cn.byxll.order.pojo.Order;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * Order业务层接口类
 * @author @By-Lin
 */
public interface OrderService {

    /**
     * 新增Order
     * @param   order      Order实体
     * @return                 响应数据
     */
    Result<Boolean> add(Order order);

    /**
     * 通过组件删除Order
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(String id);

    /**
     * 修改Order数据
     * @param order      Order实体
     * @return              响应数据
     */
    Result<Boolean> update(Order order);

    /**
     * 根据ID查询Order
     * @param id        主键id
     * @return          响应数据
     */
    Result<Order> findById(String id);

    /**
     * 查询所有Order
     * @return          响应数据
     */
    Result<List<Order>> findAll();

    /**
     * Order分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Order>> findByPager(Integer page, Integer pageSize);

    /**
     * Order条件分页查询
     * @param order      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Order>> findPagerByParam(Order order, Integer page, Integer pageSize);
    

    /**
     * Order多条件搜索方法
     * @param order      条件实体
     * @return              响应数据
     */
    Result<List<Order>> findList(Order order);
}
