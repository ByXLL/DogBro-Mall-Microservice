package cn.byxll.order.service.impl;

import cn.byxll.order.dao.TaskHisMapper;
import cn.byxll.order.pojo.TaskHis;
import cn.byxll.order.service.TaskHisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * TaskHis业务层接口实现类
 * @author By-Lin
 */
@Service
public class TaskHisServiceImpl implements TaskHisService {

    private final TaskHisMapper taskHisMapper;

    public TaskHisServiceImpl(TaskHisMapper taskHisMapper) {
        this.taskHisMapper = taskHisMapper;
    }

    /**
     * 新增TaskHis
     * @param taskHis      TaskHis实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(TaskHis taskHis){
        if(taskHis == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = taskHisMapper.insert(taskHis);
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
        int i = taskHisMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改TaskHis
     * @param taskHis      TaskHis实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(TaskHis taskHis){
        if(taskHis == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = taskHisMapper.updateByPrimaryKey(taskHis);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询TaskHis
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<TaskHis> findById(Long id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        TaskHis taskHis = taskHisMapper.selectByPrimaryKey(id);
        if(taskHis != null) { return new Result<>(true, StatusCode.OK, "查询成功", taskHis); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有TaskHis
     * @return      响应数据
     */
    @Override
    public Result<List<TaskHis>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", taskHisMapper.selectAll());
    }

    /**
     * TaskHis分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<TaskHis>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(taskHisMapper.selectAll()));
    }


    /**
     * TaskHis条件分页查询
     * @param taskHis      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<TaskHis>> findPagerByParam(TaskHis taskHis, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(taskHis);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<TaskHis>(taskHisMapper.selectByExample(example)));
    }

    /**
     * TaskHis多条件搜索方法
     * @param taskHis      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<TaskHis>> findList(TaskHis taskHis){
        if(taskHis == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(taskHis);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<TaskHis>(taskHisMapper.selectByExample(example)));
    }


    /**
     * TaskHis构建查询对象
     * @param taskHis      TaskHis实体
     * @return              查询对象
     */
    private Example createExample(TaskHis taskHis){
        Example example = new Example(TaskHis.class);
        Example.Criteria criteria = example.createCriteria();
        if(taskHis!=null){
            // 任务id
            if(!StringUtils.isEmpty(taskHis.getId())){
                criteria.andEqualTo("id",taskHis.getId());
            }
            // 
            if(!StringUtils.isEmpty(taskHis.getCreateTime())){
                criteria.andEqualTo("createTime",taskHis.getCreateTime());
            }
            // 
            if(!StringUtils.isEmpty(taskHis.getUpdateTime())){
                criteria.andEqualTo("updateTime",taskHis.getUpdateTime());
            }
            // 
            if(!StringUtils.isEmpty(taskHis.getDeleteTime())){
                criteria.andEqualTo("deleteTime",taskHis.getDeleteTime());
            }
            // 任务类型
            if(!StringUtils.isEmpty(taskHis.getTaskType())){
                criteria.andEqualTo("taskType",taskHis.getTaskType());
            }
            // 交换机名称
            if(!StringUtils.isEmpty(taskHis.getMqExchange())){
                criteria.andEqualTo("mqExchange",taskHis.getMqExchange());
            }
            // routingkey
            if(!StringUtils.isEmpty(taskHis.getMqRoutingkey())){
                criteria.andEqualTo("mqRoutingkey",taskHis.getMqRoutingkey());
            }
            // 任务请求的内容
            if(!StringUtils.isEmpty(taskHis.getRequestBody())){
                criteria.andEqualTo("requestBody",taskHis.getRequestBody());
            }
            // 任务状态
            if(!StringUtils.isEmpty(taskHis.getStatus())){
                criteria.andEqualTo("status",taskHis.getStatus());
            }
            // 
            if(!StringUtils.isEmpty(taskHis.getErrormsg())){
                criteria.andEqualTo("errormsg",taskHis.getErrormsg());
            }
        }
        return example;
    }

}
