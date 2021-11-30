package cn.byxll.order.service.impl;

import cn.byxll.order.dao.ReturnCauseMapper;
import cn.byxll.order.pojo.ReturnCause;
import cn.byxll.order.service.ReturnCauseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * ReturnCause业务层接口实现类
 * @author By-Lin
 */
@Service
public class ReturnCauseServiceImpl implements ReturnCauseService {

    private final ReturnCauseMapper returnCauseMapper;

    public ReturnCauseServiceImpl(ReturnCauseMapper returnCauseMapper) {
        this.returnCauseMapper = returnCauseMapper;
    }

    /**
     * 新增ReturnCause
     * @param returnCause      ReturnCause实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(ReturnCause returnCause){
        if(returnCause == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = returnCauseMapper.insert(returnCause);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(Integer id){
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = returnCauseMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改ReturnCause
     * @param returnCause      ReturnCause实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(ReturnCause returnCause){
        if(returnCause == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = returnCauseMapper.updateByPrimaryKey(returnCause);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询ReturnCause
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<ReturnCause> findById(Integer id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        ReturnCause returnCause = returnCauseMapper.selectByPrimaryKey(id);
        if(returnCause != null) { return new Result<>(true, StatusCode.OK, "查询成功", returnCause); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有ReturnCause
     * @return      响应数据
     */
    @Override
    public Result<List<ReturnCause>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", returnCauseMapper.selectAll());
    }

    /**
     * ReturnCause分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<ReturnCause>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(returnCauseMapper.selectAll()));
    }


    /**
     * ReturnCause条件分页查询
     * @param returnCause      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<ReturnCause>> findPagerByParam(ReturnCause returnCause, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(returnCause);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<ReturnCause>(returnCauseMapper.selectByExample(example)));
    }

    /**
     * ReturnCause多条件搜索方法
     * @param returnCause      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<ReturnCause>> findList(ReturnCause returnCause){
        if(returnCause == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(returnCause);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<ReturnCause>(returnCauseMapper.selectByExample(example)));
    }


    /**
     * ReturnCause构建查询对象
     * @param returnCause      ReturnCause实体
     * @return              查询对象
     */
    private Example createExample(ReturnCause returnCause){
        Example example = new Example(ReturnCause.class);
        Example.Criteria criteria = example.createCriteria();
        if(returnCause!=null){
            // ID
            if(!StringUtils.isEmpty(returnCause.getId())){
                criteria.andEqualTo("id",returnCause.getId());
            }
            // 原因
            if(!StringUtils.isEmpty(returnCause.getCause())){
                criteria.andEqualTo("cause",returnCause.getCause());
            }
            // 排序
            if(!StringUtils.isEmpty(returnCause.getSeq())){
                criteria.andEqualTo("seq",returnCause.getSeq());
            }
            // 是否启用
            if(!StringUtils.isEmpty(returnCause.getStatus())){
                criteria.andEqualTo("status",returnCause.getStatus());
            }
        }
        return example;
    }

}
