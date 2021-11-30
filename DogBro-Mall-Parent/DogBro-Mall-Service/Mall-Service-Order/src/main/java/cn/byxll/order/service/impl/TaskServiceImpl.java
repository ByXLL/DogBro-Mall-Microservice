package cn.byxll.order.service.impl;

import cn.byxll.order.dao.TaskMapper;
import cn.byxll.order.pojo.Task;
import cn.byxll.order.service.TaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Task业务层接口实现类
 * @author By-Lin
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    /**
     * 新增Task
     * @param task      Task实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(Task task){
        if(task == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = taskMapper.insert(task);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(Long id){
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = taskMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改Task
     * @param task      Task实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(Task task){
        if(task == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = taskMapper.updateByPrimaryKey(task);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询Task
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Task> findById(Long id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        Task task = taskMapper.selectByPrimaryKey(id);
        if(task != null) { return new Result<>(true, StatusCode.OK, "查询成功", task); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有Task
     * @return      响应数据
     */
    @Override
    public Result<List<Task>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", taskMapper.selectAll());
    }

    /**
     * Task分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Task>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(taskMapper.selectAll()));
    }


    /**
     * Task条件分页查询
     * @param task      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Task>> findPagerByParam(Task task, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(task);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<Task>(taskMapper.selectByExample(example)));
    }

    /**
     * Task多条件搜索方法
     * @param task      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<Task>> findList(Task task){
        if(task == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(task);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<Task>(taskMapper.selectByExample(example)));
    }


    /**
     * Task构建查询对象
     * @param task      Task实体
     * @return              查询对象
     */
    private Example createExample(Task task){
        Example example = new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        if(task!=null){
            // 任务id
            if(!StringUtils.isEmpty(task.getId())){
                criteria.andEqualTo("id",task.getId());
            }
            // 
            if(!StringUtils.isEmpty(task.getCreateTime())){
                criteria.andEqualTo("createTime",task.getCreateTime());
            }
            // 
            if(!StringUtils.isEmpty(task.getUpdateTime())){
                criteria.andEqualTo("updateTime",task.getUpdateTime());
            }
            // 
            if(!StringUtils.isEmpty(task.getDeleteTime())){
                criteria.andEqualTo("deleteTime",task.getDeleteTime());
            }
            // 任务类型
            if(!StringUtils.isEmpty(task.getTaskType())){
                criteria.andEqualTo("taskType",task.getTaskType());
            }
            // 交换机名称
            if(!StringUtils.isEmpty(task.getMqExchange())){
                criteria.andEqualTo("mqExchange",task.getMqExchange());
            }
            // routingkey
            if(!StringUtils.isEmpty(task.getMqRoutingkey())){
                criteria.andEqualTo("mqRoutingkey",task.getMqRoutingkey());
            }
            // 任务请求的内容
            if(!StringUtils.isEmpty(task.getRequestBody())){
                criteria.andEqualTo("requestBody",task.getRequestBody());
            }
            // 任务状态
            if(!StringUtils.isEmpty(task.getStatus())){
                criteria.andEqualTo("status",task.getStatus());
            }
            // 任务错误信息
            if(!StringUtils.isEmpty(task.getErrormsg())){
                criteria.andEqualTo("errormsg",task.getErrormsg());
            }
        }
        return example;
    }

}
