package cn.byxll.service.impl;

import cn.byxll.dao.PrefMapper;
import cn.byxll.goods.pojo.Pref;
import cn.byxll.service.PrefService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * Pref Service接口实现类
 * @Author: By-Lin
 */
@Service
public class PrefServiceImpl implements PrefService {
    private final PrefMapper prefMapper;

    public PrefServiceImpl(PrefMapper prefMapper) {
        this.prefMapper = prefMapper;
    }

    /**
     * 增加Pref
     * @param pref      Pref 实体
     * @return          响应数据
     */
    @Override
    public Result<Boolean> add(Pref pref){
        if(pref == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = prefMapper.insert(pref);
        if(i>0) { return new Result<>(true, StatusCode.OK, "添加成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id      Pref 主键id
     * @return        响应数据
     */
    @Override
    public Result<Boolean> delete(Integer id){
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = prefMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "删除成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改Pref
     * @param pref       Pref实体
     * @return           响应数据
     */
    @Override
    public Result<Boolean> update(Pref pref){
        if(pref == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = prefMapper.updateByPrimaryKey(pref);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询Pref
     * @param id        Pref 主键
     * @return          响应数据
     */
    @Override
    public Result<Pref> findById(Integer id){
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        Pref pref = prefMapper.selectByPrimaryKey(id);
        if(pref == null) { return new Result<>(false, StatusCode.ERROR, "查询失败",null); }
        return new Result<>(true, StatusCode.OK, "查询成功", pref);
    }

    /**
     * Pref分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Pref>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(prefMapper.selectAll()));
    }

    /**
     * Pref条件+分页查询
     * @param pref      查询条件
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Pref>> findPagerByParam(Pref pref, Integer page, Integer pageSize){
        if(pref == null || page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        // 静态分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(pref);
        //分页
        PageHelper.startPage(page,pageSize);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "查询成功",  new PageInfo<>(prefMapper.selectByExample(example)));
    }

    /**
     * Pref条件查询
     * @param pref      Pref实体
     * @return          响应数据
     */
    @Override
    public Result<List<Pref>> findListByParam(Pref pref){
        if(pref == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //构建查询条件
        Example example = createExample(pref);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "查询成功", prefMapper.selectByExample(example));
    }

    /**
     * 查询Pref全部数据
     * @return      响应数据
     */
    @Override
    public Result<List<Pref>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", prefMapper.selectAll());
    }

    /**
     * Pref构建查询对象
     * @param pref      pref实体
     * @return              Example实体
     */
    public Example createExample(Pref pref){
        Example example=new Example(Pref.class);
        Example.Criteria criteria = example.createCriteria();
        if(pref!=null){
            // ID
            if(!StringUtils.isEmpty(String.valueOf(pref.getId()))){
                criteria.andEqualTo("id",pref.getId());
            }
            // 分类ID
            if(!StringUtils.isEmpty(String.valueOf(pref.getCateId()))){
                criteria.andEqualTo("cateId",pref.getCateId());
            }
            // 消费金额
            if(!StringUtils.isEmpty(String.valueOf(pref.getBuyMoney()))){
                criteria.andEqualTo("buyMoney",pref.getBuyMoney());
            }
            // 优惠金额
            if(!StringUtils.isEmpty(String.valueOf(pref.getPreMoney()))){
                criteria.andEqualTo("preMoney",pref.getPreMoney());
            }
            // 活动开始日期
            if(!StringUtils.isEmpty(String.valueOf(pref.getStartTime()))){
                criteria.andEqualTo("startTime",pref.getStartTime());
            }
            // 活动截至日期
            if(!StringUtils.isEmpty(String.valueOf(pref.getEndTime()))){
                criteria.andEqualTo("endTime",pref.getEndTime());
            }
            // 类型
            if(!StringUtils.isEmpty(pref.getType())){
                criteria.andEqualTo("type",pref.getType());
            }
            // 状态
            if(!StringUtils.isEmpty(pref.getState())){
                criteria.andEqualTo("state",pref.getState());
            }
        }
        return example;
    }
}
