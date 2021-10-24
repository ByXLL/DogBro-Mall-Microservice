package cn.byxll.goods.service.impl;

import cn.byxll.goods.dao.CategoryBrandMapper;
import cn.byxll.goods.pojo.CategoryBrand;
import cn.byxll.goods.service.CategoryBrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * CategoryBrand Service接口实现类
 * @Author: By-Lin
 */
@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {
    private final CategoryBrandMapper categoryBrandMapper;

    public CategoryBrandServiceImpl(CategoryBrandMapper categoryBrandMapper) {
        this.categoryBrandMapper = categoryBrandMapper;
    }

    /**
     * 增加CategoryBrand
     * @param categoryBrand      CategoryBrand 实体
     * @return                   响应数据
     */
    @Override
    public Result<Boolean> add(CategoryBrand categoryBrand){
        if(categoryBrand == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = categoryBrandMapper.insert(categoryBrand);
        if(i>0) { return new Result<>(true, StatusCode.OK, "添加成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id      CategoryBrand 主键id
     * @return        响应数据
     */
    @Override
    public Result<Boolean> delete(Integer id){
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = categoryBrandMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "删除成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改CategoryBrand
     * @param categoryBrand       CategoryBrand实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> update(CategoryBrand categoryBrand){
        if(categoryBrand == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = categoryBrandMapper.updateByPrimaryKey(categoryBrand);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询CategoryBrand
     * @param id        CategoryBrand 主键
     * @return          响应数据
     */
    @Override
    public Result<CategoryBrand> findById(Integer id){
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        CategoryBrand categoryBrand = categoryBrandMapper.selectByPrimaryKey(id);
        if(categoryBrand == null) { return new Result<>(false, StatusCode.ERROR, "查询失败",null); }
        return new Result<>(true, StatusCode.OK, "查询成功", categoryBrand);
    }

    /**
     * CategoryBrand分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<CategoryBrand>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(categoryBrandMapper.selectAll()));
    }

    /**
     * CategoryBrand条件+分页查询
     * @param categoryBrand      查询条件
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<CategoryBrand>> findPagerByParam(CategoryBrand categoryBrand, Integer page, Integer pageSize){
        if(categoryBrand == null || page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        // 静态分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(categoryBrand);
        //分页
        PageHelper.startPage(page,pageSize);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(categoryBrandMapper.selectByExample(example)));
    }

    /**
     * CategoryBrand条件查询
     * @param categoryBrand      CategoryBrand实体
     * @return              响应数据
     */
    @Override
    public Result<List<CategoryBrand>> findListByParam(CategoryBrand categoryBrand){
        if(categoryBrand == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //构建查询条件
        Example example = createExample(categoryBrand);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "查询成功", categoryBrandMapper.selectByExample(example));
    }

    /**
     * 查询CategoryBrand全部数据
     * @return      响应数据
     */
    @Override
    public Result<List<CategoryBrand>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", categoryBrandMapper.selectAll());
    }

    /**
     * CategoryBrand构建查询对象
     * @param categoryBrand      categoryBrand实体
     * @return                   Example实体
     */
    public Example createExample(CategoryBrand categoryBrand){
        Example example=new Example(CategoryBrand.class);
        Example.Criteria criteria = example.createCriteria();
        if(categoryBrand!=null){
            // 分类ID
            if(!StringUtils.isEmpty(String.valueOf(categoryBrand.getCategoryId()))){
                criteria.andEqualTo("categoryId",categoryBrand.getCategoryId());
            }
            // 品牌ID
            if(!StringUtils.isEmpty(String.valueOf(categoryBrand.getBrandId()))){
                criteria.andEqualTo("brandId",categoryBrand.getBrandId());
            }
        }
        return example;
    }
}
