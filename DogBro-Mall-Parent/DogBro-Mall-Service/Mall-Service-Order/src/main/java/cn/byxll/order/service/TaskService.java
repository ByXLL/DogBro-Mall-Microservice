package cn.byxll.order.service;

import cn.byxll.order.pojo.Task;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * Task业务层接口类
 * @author @By-Lin
 */
public interface TaskService {

    /**
     * 新增Task
     * @param   task      Task实体
     * @return                 响应数据
     */
    Result<Boolean> add(Task task);

    /**
     * 通过组件删除Task
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(Long id);

    /**
     * 修改Task数据
     * @param task      Task实体
     * @return              响应数据
     */
    Result<Boolean> update(Task task);

    /**
     * 根据ID查询Task
     * @param id        主键id
     * @return          响应数据
     */
    Result<Task> findById(Long id);

    /**
     * 查询所有Task
     * @return          响应数据
     */
    Result<List<Task>> findAll();

    /**
     * Task分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Task>> findByPager(Integer page, Integer pageSize);

    /**
     * Task条件分页查询
     * @param task      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Task>> findPagerByParam(Task task, Integer page, Integer pageSize);
    

    /**
     * Task多条件搜索方法
     * @param task      条件实体
     * @return              响应数据
     */
    Result<List<Task>> findList(Task task);
}
