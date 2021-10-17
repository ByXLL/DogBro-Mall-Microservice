package cn.byxll.service.impl;

import cn.byxll.dao.CategoryMapper;
import cn.byxll.goods.pojo.Category;
import cn.byxll.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 商品分类 service 接口实现类
 * @author By-Lin
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     * 添加商品分类
     * @param category 商品分类实体
     * @return 响应数据
     */
    @Override
    public Result<Boolean> add(Category category) {
        if(category == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = categoryMapper.insert(category);
        if(i>0) { return new Result<>(true,StatusCode.OK,"添加成功"); }
        return new Result<>(false,StatusCode.ERROR,"添加失败");
    }

    /**
     * 删除商品分类
     * @param id 商品分类id
     * @return 响应数据
     */
    @Override
    public Result<Boolean> delete(Integer id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = categoryMapper.deleteByPrimaryKey(id);
        if(i >0) { return new Result<>(true, StatusCode.OK, "删除成功"); }
        return new Result<>(false, StatusCode.ERROR, "删除失败");
    }

    /**
     * 编辑商品分类
     * @param category 商品分类实体
     * @return 响应数据
     */
    @Override
    public Result<Boolean> update(Category category) {
        if(category == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = categoryMapper.updateByPrimaryKey(category);
        if(i >0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 通过id查询
     * @param id 商品分类
     * @return 响应数据
     */
    @Override
    public Result<Category> findById(Integer id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        Category category = categoryMapper.selectByPrimaryKey(id);
        if(category == null ) { return new Result<>(false, StatusCode.ERROR, "查询失败",null); }
        return new Result<>(true, StatusCode.OK, "查询成功",category);
    }

    /**
     * 分页查询
     * @param page     当前页码
     * @param pageSize 每页大小
     * @return 响应数据
     */
    @Override
    public Result<PageInfo<Category>> findByPager(Integer page, Integer pageSize) {
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        PageHelper.startPage(page,pageSize);
        List<Category> categoryList = categoryMapper.selectAll();
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(categoryList));
    }

    /**
     * 按条件分页查询
     * @param category 商品分类实体
     * @param page     当前页码
     * @param pageSize 每页大小
     * @return 响应数据
     */
    @Override
    public Result<PageInfo<Category>> findPagerByParam(Category category, Integer page, Integer pageSize) {
        if(category == null || page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        Example example = createExample(category);
        PageHelper.startPage(page,pageSize);
        List<Category> categoryList = categoryMapper.selectByExample(example);
        return new Result<>(true, StatusCode.OK, "查询成功",new PageInfo<>(categoryList));
    }

    /**
     * 根据父节点查询
     * @param pId 父节点id
     * @return 响应数据
     */
    @Override
    public Result<List<Category>> findByParentId(Integer pId) {
        if(pId == null) { return new Result<>(false, StatusCode.ARGERROR,"参数异常",null); }
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId",pId);
        List<Category> categoryList = categoryMapper.selectByExample(example);
        return new Result<>(true,StatusCode.OK, "查询成功",categoryList);
    }

    /**
     * 根据模板id查询集合
     *
     * @param tId 模板id
     * @return 响应数据
     */
    @Override
    public Result<List<Category>> findByTemplateId(Integer tId) {
        if(tId == null) { return new Result<>(false,StatusCode.ARGERROR,"参数异常",null); }
        Category category = new Category();
        category.setTemplateId(tId);
        Example example = createExample(category);
        List<Category> categoryList = categoryMapper.selectByExample(example);
        return new Result<>(true, StatusCode.OK, "查询成功",categoryList);
    }

    /**
     * 查询所有分类
     *
     * @return 响应数据
     */
    @Override
    public Result<List<Category>> findAll() {
        List<Category> categoryList = categoryMapper.selectAll();
        return new Result<>(true, StatusCode.OK, "查询成功", categoryList);
    }


    /**
     * 生成查询条件
     * @param category     商品分类实体
     * @return             查询条件 example
     */
    private Example createExample(Category category) {
        // 自定义条件搜索对象 Example
        Example example = new Example(Category.class);
        // 构建条件构造器
        Example.Criteria criteria = example.createCriteria();
        if(category!= null) {
            if(!StringUtils.isEmpty((category.getName()))) {
                criteria.andLike("name","%"+category.getName()+"%");
            }
            if(!StringUtils.isEmpty(category.getIsShow())) {
                criteria.andEqualTo("isShow",category.getIsShow());
            }
            if(!StringUtils.isEmpty(category.getIsMenu())) {
                criteria.andEqualTo("isMenu",category.getIsMenu());
            }
            if(!StringUtils.isEmpty(String.valueOf(category.getSeq()))) {
                criteria.andEqualTo("seq",category.getIsMenu());
            }
            if(!StringUtils.isEmpty(String.valueOf(category.getParentId()))) {
                criteria.andEqualTo("parentId",category.getParentId());
            }
            if(!StringUtils.isEmpty(String.valueOf(category.getTemplateId()))) {
                criteria.andEqualTo("templateId",category.getTemplateId());
            }
        }
        return example;
    }
}
