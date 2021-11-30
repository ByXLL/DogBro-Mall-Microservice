package cn.byxll.order.service;

import cn.byxll.order.pojo.TaskHis;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * TaskHis业务层接口类
 * @author @By-Lin
 */
public interface TaskHisService {

    /**
     * 新增TaskHis
     * @param   taskHis      TaskHis实体
     * @return                 响应数据
     */
    Result<Boolean> add(TaskHis taskHis);

    /**
     * 通过组件删除TaskHis
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(Long id);

    /**
     * 修改TaskHis数据
     * @param taskHis      TaskHis实体
     * @return              响应数据
     */
    Result<Boolean> update(TaskHis taskHis);

    /**
     * 根据ID查询TaskHis
     * @param id        主键id
     * @return          响应数据
     */
    Result<TaskHis> findById(Long id);

    /**
     * 查询所有TaskHis
     * @return          响应数据
     */
    Result<List<TaskHis>> findAll();

    /**
     * TaskHis分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<TaskHis>> findByPager(Integer page, Integer pageSize);

    /**
     * TaskHis条件分页查询
     * @param taskHis      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<TaskHis>> findPagerByParam(TaskHis taskHis, Integer page, Integer pageSize);
    

    /**
     * TaskHis多条件搜索方法
     * @param taskHis      条件实体
     * @return              响应数据
     */
    Result<List<TaskHis>> findList(TaskHis taskHis);
}
