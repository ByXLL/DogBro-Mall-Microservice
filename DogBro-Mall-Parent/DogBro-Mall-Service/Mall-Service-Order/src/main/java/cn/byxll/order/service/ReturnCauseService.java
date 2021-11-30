package cn.byxll.order.service;

import cn.byxll.order.pojo.ReturnCause;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * ReturnCause业务层接口类
 * @author @By-Lin
 */
public interface ReturnCauseService {

    /**
     * 新增ReturnCause
     * @param   returnCause      ReturnCause实体
     * @return                 响应数据
     */
    Result<Boolean> add(ReturnCause returnCause);

    /**
     * 通过组件删除ReturnCause
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(Integer id);

    /**
     * 修改ReturnCause数据
     * @param returnCause      ReturnCause实体
     * @return              响应数据
     */
    Result<Boolean> update(ReturnCause returnCause);

    /**
     * 根据ID查询ReturnCause
     * @param id        主键id
     * @return          响应数据
     */
    Result<ReturnCause> findById(Integer id);

    /**
     * 查询所有ReturnCause
     * @return          响应数据
     */
    Result<List<ReturnCause>> findAll();

    /**
     * ReturnCause分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<ReturnCause>> findByPager(Integer page, Integer pageSize);

    /**
     * ReturnCause条件分页查询
     * @param returnCause      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<ReturnCause>> findPagerByParam(ReturnCause returnCause, Integer page, Integer pageSize);
    

    /**
     * ReturnCause多条件搜索方法
     * @param returnCause      条件实体
     * @return              响应数据
     */
    Result<List<ReturnCause>> findList(ReturnCause returnCause);
}
