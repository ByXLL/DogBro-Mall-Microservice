package cn.byxll.order.service;

import cn.byxll.order.pojo.ReturnOrder;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * ReturnOrder业务层接口类
 * @author @By-Lin
 */
public interface ReturnOrderService {

    /**
     * 新增ReturnOrder
     * @param   returnOrder      ReturnOrder实体
     * @return                 响应数据
     */
    Result<Boolean> add(ReturnOrder returnOrder);

    /**
     * 通过组件删除ReturnOrder
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(String id);

    /**
     * 修改ReturnOrder数据
     * @param returnOrder      ReturnOrder实体
     * @return              响应数据
     */
    Result<Boolean> update(ReturnOrder returnOrder);

    /**
     * 根据ID查询ReturnOrder
     * @param id        主键id
     * @return          响应数据
     */
    Result<ReturnOrder> findById(String id);

    /**
     * 查询所有ReturnOrder
     * @return          响应数据
     */
    Result<List<ReturnOrder>> findAll();

    /**
     * ReturnOrder分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<ReturnOrder>> findByPager(Integer page, Integer pageSize);

    /**
     * ReturnOrder条件分页查询
     * @param returnOrder      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<ReturnOrder>> findPagerByParam(ReturnOrder returnOrder, Integer page, Integer pageSize);
    

    /**
     * ReturnOrder多条件搜索方法
     * @param returnOrder      条件实体
     * @return              响应数据
     */
    Result<List<ReturnOrder>> findList(ReturnOrder returnOrder);
}
