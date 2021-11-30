package cn.byxll.order.service;

import cn.byxll.order.pojo.UndoLog;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * UndoLog业务层接口类
 * @author @By-Lin
 */
public interface UndoLogService {

    /**
     * 新增UndoLog
     * @param   undoLog      UndoLog实体
     * @return                 响应数据
     */
    Result<Boolean> add(UndoLog undoLog);

    /**
     * 通过组件删除UndoLog
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(Long id);

    /**
     * 修改UndoLog数据
     * @param undoLog      UndoLog实体
     * @return              响应数据
     */
    Result<Boolean> update(UndoLog undoLog);

    /**
     * 根据ID查询UndoLog
     * @param id        主键id
     * @return          响应数据
     */
    Result<UndoLog> findById(Long id);

    /**
     * 查询所有UndoLog
     * @return          响应数据
     */
    Result<List<UndoLog>> findAll();

    /**
     * UndoLog分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<UndoLog>> findByPager(Integer page, Integer pageSize);

    /**
     * UndoLog条件分页查询
     * @param undoLog      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<UndoLog>> findPagerByParam(UndoLog undoLog, Integer page, Integer pageSize);
    

    /**
     * UndoLog多条件搜索方法
     * @param undoLog      条件实体
     * @return              响应数据
     */
    Result<List<UndoLog>> findList(UndoLog undoLog);
}
