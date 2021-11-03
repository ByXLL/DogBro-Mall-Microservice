package cn.byxll.user.service.impl;

import cn.byxll.user.dao.CitiesMapper;
import cn.byxll.user.pojo.Cities;
import cn.byxll.user.service.CitiesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Cities业务层接口实现类
 * @author By-Lin
 */
@Service
public class CitiesServiceImpl implements CitiesService {

    private final CitiesMapper citiesMapper;

    public CitiesServiceImpl(CitiesMapper citiesMapper) {
        this.citiesMapper = citiesMapper;
    }

    /**
     * 新增Cities
     * @param cities      Cities实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(Cities cities){
        if(cities == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = citiesMapper.insert(cities);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(String id){
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = citiesMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改Cities
     * @param cities      Cities实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(Cities cities){
        if(cities == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = citiesMapper.updateByPrimaryKey(cities);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询Cities
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Cities> findById(String id) {
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        Cities cities = citiesMapper.selectByPrimaryKey(id);
        if(cities != null) { return new Result<>(true, StatusCode.OK, "查询成功", cities); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有Cities
     * @return      响应数据
     */
    @Override
    public Result<List<Cities>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", citiesMapper.selectAll());
    }

    /**
     * Cities分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Cities>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(citiesMapper.selectAll()));
    }


    /**
     * Cities条件分页查询
     * @param cities      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Cities>> findPagerByParam(Cities cities, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(cities);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<Cities>(citiesMapper.selectByExample(example)));
    }

    /**
     * Cities多条件搜索方法
     * @param cities      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<Cities>> findList(Cities cities){
        if(cities == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(cities);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<Cities>(citiesMapper.selectByExample(example)));
    }


    /**
     * Cities构建查询对象
     * @param cities      Cities实体
     * @return              查询对象
     */
    private Example createExample(Cities cities){
        Example example = new Example(Cities.class);
        Example.Criteria criteria = example.createCriteria();
        if(cities!=null){
            // 城市ID
            if(!StringUtils.isEmpty(cities.getCityId())){
                criteria.andEqualTo("cityid",cities.getCityId());
            }
            // 城市名称
            if(!StringUtils.isEmpty(cities.getCity())){
                criteria.andEqualTo("city",cities.getCity());
            }
            // 省份ID
            if(!StringUtils.isEmpty(cities.getProvinceId())){
                criteria.andEqualTo("provinceid",cities.getProvinceId());
            }
        }
        return example;
    }

}
