package cn.byxll.order.service;

import cn.byxll.order.pojo.OrderConfig;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * OrderConfig业务层接口类
 * @author @By-Lin
 */
public interface OrderConfigService {

    /**
     * 新增OrderConfig
     * @param   orderConfig      OrderConfig实体
     * @return                 响应数据
     */
    Result<Boolean> add(OrderConfig orderConfig);

    /**
     * 通过组件删除OrderConfig
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(Integer id);

    /**
     * 修改OrderConfig数据
     * @param orderConfig      OrderConfig实体
     * @return              响应数据
     */
    Result<Boolean> update(OrderConfig orderConfig);

    /**
     * 根据ID查询OrderConfig
     * @param id        主键id
     * @return          响应数据
     */
    Result<OrderConfig> findById(Integer id);

    /**
     * 查询所有OrderConfig
     * @return          响应数据
     */
    Result<List<OrderConfig>> findAll();

    /**
     * OrderConfig分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<OrderConfig>> findByPager(Integer page, Integer pageSize);

    /**
     * OrderConfig条件分页查询
     * @param orderConfig      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<OrderConfig>> findPagerByParam(OrderConfig orderConfig, Integer page, Integer pageSize);
    

    /**
     * OrderConfig多条件搜索方法
     * @param orderConfig      条件实体
     * @return              响应数据
     */
    Result<List<OrderConfig>> findList(OrderConfig orderConfig);
}
