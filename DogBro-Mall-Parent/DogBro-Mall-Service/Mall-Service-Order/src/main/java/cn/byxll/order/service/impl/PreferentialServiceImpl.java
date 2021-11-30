package cn.byxll.order.service.impl;

import cn.byxll.order.dao.PreferentialMapper;
import cn.byxll.order.pojo.Preferential;
import cn.byxll.order.service.PreferentialService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Preferential业务层接口实现类
 * @author By-Lin
 */
@Service
public class PreferentialServiceImpl implements PreferentialService {

    private final PreferentialMapper preferentialMapper;

    public PreferentialServiceImpl(PreferentialMapper preferentialMapper) {
        this.preferentialMapper = preferentialMapper;
    }

    /**
     * 新增Preferential
     * @param preferential      Preferential实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(Preferential preferential){
        if(preferential == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = preferentialMapper.insert(preferential);
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
        int i = preferentialMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改Preferential
     * @param preferential      Preferential实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(Preferential preferential){
        if(preferential == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = preferentialMapper.updateByPrimaryKey(preferential);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询Preferential
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Preferential> findById(Integer id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        Preferential preferential = preferentialMapper.selectByPrimaryKey(id);
        if(preferential != null) { return new Result<>(true, StatusCode.OK, "查询成功", preferential); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有Preferential
     * @return      响应数据
     */
    @Override
    public Result<List<Preferential>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", preferentialMapper.selectAll());
    }

    /**
     * Preferential分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Preferential>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(preferentialMapper.selectAll()));
    }


    /**
     * Preferential条件分页查询
     * @param preferential      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Preferential>> findPagerByParam(Preferential preferential, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(preferential);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<Preferential>(preferentialMapper.selectByExample(example)));
    }

    /**
     * Preferential多条件搜索方法
     * @param preferential      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<Preferential>> findList(Preferential preferential){
        if(preferential == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(preferential);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<Preferential>(preferentialMapper.selectByExample(example)));
    }


    /**
     * Preferential构建查询对象
     * @param preferential      Preferential实体
     * @return              查询对象
     */
    private Example createExample(Preferential preferential){
        Example example = new Example(Preferential.class);
        Example.Criteria criteria = example.createCriteria();
        if(preferential!=null){
            // ID
            if(!StringUtils.isEmpty(preferential.getId())){
                criteria.andEqualTo("id",preferential.getId());
            }
            // 消费金额
            if(!StringUtils.isEmpty(preferential.getBuyMoney())){
                criteria.andEqualTo("buyMoney",preferential.getBuyMoney());
            }
            // 优惠金额
            if(!StringUtils.isEmpty(preferential.getPreMoney())){
                criteria.andEqualTo("preMoney",preferential.getPreMoney());
            }
            // 品类ID
            if(!StringUtils.isEmpty(preferential.getCategoryId())){
                criteria.andEqualTo("categoryId",preferential.getCategoryId());
            }
            // 活动开始日期
            if(!StringUtils.isEmpty(preferential.getStartTime())){
                criteria.andEqualTo("startTime",preferential.getStartTime());
            }
            // 活动截至日期
            if(!StringUtils.isEmpty(preferential.getEndTime())){
                criteria.andEqualTo("endTime",preferential.getEndTime());
            }
            // 状态
            if(!StringUtils.isEmpty(preferential.getState())){
                criteria.andEqualTo("state",preferential.getState());
            }
            // 类型1不翻倍 2翻倍
            if(!StringUtils.isEmpty(preferential.getType())){
                criteria.andEqualTo("type",preferential.getType());
            }
        }
        return example;
    }

}
