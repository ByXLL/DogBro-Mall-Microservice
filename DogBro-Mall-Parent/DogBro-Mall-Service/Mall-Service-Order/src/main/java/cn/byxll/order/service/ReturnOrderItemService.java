package cn.byxll.order.service;

import cn.byxll.order.pojo.ReturnOrderItem;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * ReturnOrderItem业务层接口类
 * @author @By-Lin
 */
public interface ReturnOrderItemService {

    /**
     * 新增ReturnOrderItem
     * @param   returnOrderItem      ReturnOrderItem实体
     * @return                 响应数据
     */
    Result<Boolean> add(ReturnOrderItem returnOrderItem);

    /**
     * 通过组件删除ReturnOrderItem
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(String id);

    /**
     * 修改ReturnOrderItem数据
     * @param returnOrderItem      ReturnOrderItem实体
     * @return              响应数据
     */
    Result<Boolean> update(ReturnOrderItem returnOrderItem);

    /**
     * 根据ID查询ReturnOrderItem
     * @param id        主键id
     * @return          响应数据
     */
    Result<ReturnOrderItem> findById(String id);

    /**
     * 查询所有ReturnOrderItem
     * @return          响应数据
     */
    Result<List<ReturnOrderItem>> findAll();

    /**
     * ReturnOrderItem分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<ReturnOrderItem>> findByPager(Integer page, Integer pageSize);

    /**
     * ReturnOrderItem条件分页查询
     * @param returnOrderItem      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<ReturnOrderItem>> findPagerByParam(ReturnOrderItem returnOrderItem, Integer page, Integer pageSize);
    

    /**
     * ReturnOrderItem多条件搜索方法
     * @param returnOrderItem      条件实体
     * @return              响应数据
     */
    Result<List<ReturnOrderItem>> findList(ReturnOrderItem returnOrderItem);
}
