package cn.byxll.user.service.impl;

import cn.byxll.user.dao.ProvincesMapper;
import cn.byxll.user.pojo.Provinces;
import cn.byxll.user.service.ProvincesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Provinces业务层接口实现类
 * @author By-Lin
 */
@Service
public class ProvincesServiceImpl implements ProvincesService {

    private final ProvincesMapper provincesMapper;

    public ProvincesServiceImpl(ProvincesMapper provincesMapper) {
        this.provincesMapper = provincesMapper;
    }

    /**
     * 新增Provinces
     * @param provinces      Provinces实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(Provinces provinces){
        if(provinces == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = provincesMapper.insert(provinces);
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
        int i = provincesMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改Provinces
     * @param provinces      Provinces实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(Provinces provinces){
        if(provinces == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = provincesMapper.updateByPrimaryKey(provinces);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询Provinces
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Provinces> findById(String id) {
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        Provinces provinces = provincesMapper.selectByPrimaryKey(id);
        if(provinces != null) { return new Result<>(true, StatusCode.OK, "查询成功", provinces); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有Provinces
     * @return      响应数据
     */
    @Override
    public Result<List<Provinces>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", provincesMapper.selectAll());
    }

    /**
     * Provinces分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Provinces>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(provincesMapper.selectAll()));
    }


    /**
     * Provinces条件分页查询
     * @param provinces      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Provinces>> findPagerByParam(Provinces provinces, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(provinces);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<Provinces>(provincesMapper.selectByExample(example)));
    }

    /**
     * Provinces多条件搜索方法
     * @param provinces      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<Provinces>> findList(Provinces provinces){
        if(provinces == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(provinces);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<Provinces>(provincesMapper.selectByExample(example)));
    }


    /**
     * Provinces构建查询对象
     * @param provinces      Provinces实体
     * @return              查询对象
     */
    private Example createExample(Provinces provinces){
        Example example = new Example(Provinces.class);
        Example.Criteria criteria = example.createCriteria();
        if(provinces!=null){
            // 省份ID
            if(!StringUtils.isEmpty(provinces.getProvinceId())){
                criteria.andEqualTo("provinceid",provinces.getProvinceId());
            }
            // 省份名称
            if(!StringUtils.isEmpty(provinces.getProvince())){
                criteria.andEqualTo("province",provinces.getProvince());
            }
        }
        return example;
    }

}
