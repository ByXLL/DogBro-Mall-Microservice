package cn.byxll.user.service.impl;

import cn.byxll.user.dao.UndoLogMapper;
import cn.byxll.user.pojo.UndoLog;
import cn.byxll.user.service.UndoLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * UndoLog业务层接口实现类
 * @author By-Lin
 */
@Service
public class UndoLogServiceImpl implements UndoLogService {

    private final UndoLogMapper undoLogMapper;

    public UndoLogServiceImpl(UndoLogMapper undoLogMapper) {
        this.undoLogMapper = undoLogMapper;
    }

    /**
     * 新增UndoLog
     * @param undoLog      UndoLog实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(UndoLog undoLog){
        if(undoLog == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = undoLogMapper.insert(undoLog);
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
        int i = undoLogMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改UndoLog
     * @param undoLog      UndoLog实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(UndoLog undoLog){
        if(undoLog == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = undoLogMapper.updateByPrimaryKey(undoLog);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询UndoLog
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<UndoLog> findById(Long id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        UndoLog undoLog = undoLogMapper.selectByPrimaryKey(id);
        if(undoLog != null) { return new Result<>(true, StatusCode.OK, "查询成功", undoLog); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有UndoLog
     * @return      响应数据
     */
    @Override
    public Result<List<UndoLog>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", undoLogMapper.selectAll());
    }

    /**
     * UndoLog分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<UndoLog>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(undoLogMapper.selectAll()));
    }


    /**
     * UndoLog条件分页查询
     * @param undoLog      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<UndoLog>> findPagerByParam(UndoLog undoLog, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(undoLog);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<UndoLog>(undoLogMapper.selectByExample(example)));
    }

    /**
     * UndoLog多条件搜索方法
     * @param undoLog      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<UndoLog>> findList(UndoLog undoLog){
        if(undoLog == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(undoLog);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<UndoLog>(undoLogMapper.selectByExample(example)));
    }


    /**
     * UndoLog构建查询对象
     * @param undoLog      UndoLog实体
     * @return              查询对象
     */
    private Example createExample(UndoLog undoLog){
        Example example = new Example(UndoLog.class);
        Example.Criteria criteria = example.createCriteria();
        if(undoLog!=null){
            // 
            if(!StringUtils.isEmpty(undoLog.getId())){
                criteria.andEqualTo("id",undoLog.getId());
            }
            // 
            if(!StringUtils.isEmpty(undoLog.getBranchId())){
                criteria.andEqualTo("branchId",undoLog.getBranchId());
            }
            // 
            if(!StringUtils.isEmpty(undoLog.getXid())){
                criteria.andEqualTo("xid",undoLog.getXid());
            }
            // 
            if(!StringUtils.isEmpty(undoLog.getRollbackInfo())){
                criteria.andEqualTo("rollbackInfo",undoLog.getRollbackInfo());
            }
            // 
            if(!StringUtils.isEmpty(undoLog.getLogStatus())){
                criteria.andEqualTo("logStatus",undoLog.getLogStatus());
            }
            // 
            if(!StringUtils.isEmpty(undoLog.getLogCreated())){
                criteria.andEqualTo("logCreated",undoLog.getLogCreated());
            }
            // 
            if(!StringUtils.isEmpty(undoLog.getLogModified())){
                criteria.andEqualTo("logModified",undoLog.getLogModified());
            }
            // 
            if(!StringUtils.isEmpty(undoLog.getExt())){
                criteria.andEqualTo("ext",undoLog.getExt());
            }
        }
        return example;
    }

}
