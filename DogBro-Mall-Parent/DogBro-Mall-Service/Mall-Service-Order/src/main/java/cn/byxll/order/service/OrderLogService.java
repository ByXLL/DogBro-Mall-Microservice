package cn.byxll.order.service;

import cn.byxll.order.pojo.OrderLog;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * OrderLog业务层接口类
 * @author @By-Lin
 */
public interface OrderLogService {

    /**
     * 新增OrderLog
     * @param   orderLog      OrderLog实体
     * @return                 响应数据
     */
    Result<Boolean> add(OrderLog orderLog);

    /**
     * 通过组件删除OrderLog
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(String id);

    /**
     * 修改OrderLog数据
     * @param orderLog      OrderLog实体
     * @return              响应数据
     */
    Result<Boolean> update(OrderLog orderLog);

    /**
     * 根据ID查询OrderLog
     * @param id        主键id
     * @return          响应数据
     */
    Result<OrderLog> findById(String id);

    /**
     * 查询所有OrderLog
     * @return          响应数据
     */
    Result<List<OrderLog>> findAll();

    /**
     * OrderLog分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<OrderLog>> findByPager(Integer page, Integer pageSize);

    /**
     * OrderLog条件分页查询
     * @param orderLog      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<OrderLog>> findPagerByParam(OrderLog orderLog, Integer page, Integer pageSize);
    

    /**
     * OrderLog多条件搜索方法
     * @param orderLog      条件实体
     * @return              响应数据
     */
    Result<List<OrderLog>> findList(OrderLog orderLog);
}
