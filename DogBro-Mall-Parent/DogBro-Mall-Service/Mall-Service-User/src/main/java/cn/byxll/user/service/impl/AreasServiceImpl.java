package cn.byxll.user.service.impl;


import cn.byxll.user.dao.AreasMapper;
import cn.byxll.user.pojo.Areas;
import cn.byxll.user.service.AreasService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 行政区域县区信息 接口实现类
 * @author By-Lin
 */
@Service
public class AreasServiceImpl implements AreasService {

    private final AreasMapper areasMapper;

    public AreasServiceImpl(AreasMapper areasMapper) {
        this.areasMapper = areasMapper;
    }

    /**
     * 添加区域县区信息
     * @param areas     区县信息实体
     * @return          响应数据
     */
    @Override
    public Result<Boolean> add(Areas areas) {
        if(areas == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = areasMapper.insert(areas);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除Areas
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(String id) {
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = areasMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改Areas数据
     * @param areas     区县信息实体
     * @return          响应数据
     */
    @Override
    public Result<Boolean> update(Areas areas) {
        if(areas == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = areasMapper.updateByPrimaryKey(areas);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询Areas
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Areas> findById(String id) {
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        Areas areas = areasMapper.selectByPrimaryKey(id);
        if(areas != null) { return new Result<>(true, StatusCode.OK, "查询成功", areas); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有Areas
     * @return      响应数据
     */
    @Override
    public Result<List<Areas>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", areasMapper.selectAll());
    }

    /**
     * Areas多条件搜索
     * @param areas         条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<Areas>> findList(Areas areas) {
        if(areas == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(areas);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "查询成功", areasMapper.selectByExample(example));
    }

    /**
     * Areas分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Areas>> findByPager(Integer page, Integer pageSize) {
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(areasMapper.selectAll()));
    }

    /**
     * Areas多条件分页查询
     * @param areas         条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Areas>> findPagerByParam(Areas areas, Integer page, Integer pageSize) {
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(areas);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<>(areasMapper.selectByExample(example)));
    }


    /**
     * Areas构建查询对象
     * @param areas         实体
     * @return              查询对象
     */
    private Example createExample(Areas areas){
        Example example=new Example(Areas.class);
        Example.Criteria criteria = example.createCriteria();
        if(areas!=null){
            // 区域ID
            if(!StringUtils.isEmpty(areas.getAreaId())){
                criteria.andEqualTo("areaid",areas.getAreaId());
            }
            // 区域名称
            if(!StringUtils.isEmpty(areas.getArea())){
                criteria.andEqualTo("area",areas.getArea());
            }
            // 城市ID
            if(!StringUtils.isEmpty(areas.getCityId())){
                criteria.andEqualTo("cityid",areas.getCityId());
            }
        }
        return example;
    }
}
