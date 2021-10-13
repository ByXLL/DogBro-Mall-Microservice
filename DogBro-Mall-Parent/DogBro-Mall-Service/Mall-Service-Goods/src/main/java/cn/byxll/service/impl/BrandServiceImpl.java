package cn.byxll.service.impl;

import cn.byxll.dao.BrandMapper;
import cn.byxll.goods.pojo.Brand;
import cn.byxll.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 品牌接口实现类
 * @author By-Lin
 */
@Service
public class BrandServiceImpl implements BrandService {

    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    /**
     * 查询所有品牌
     * @return      响应数据
     */
    @Override
    public Result<List<Brand>> findAll() {
        return new Result<>(true, StatusCode.OK, "获取成功", brandMapper.selectAll());
    }

    /**
     * 通过id查询一个品牌
     * @param id 品牌id
     * @return 响应数据
     */
    @Override
    public Result<Brand> findById(Integer id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        return new Result<>(true, StatusCode.OK, "查询成功", brandMapper.selectByPrimaryKey(id));
    }

    /**
     * 通过条件查询品牌
     * @param brand 品牌实体
     * @return 响应数据
     */
    @Override
    public Result<List<Brand>> findByEntity(Brand brand) {
        if(brand == null) {
            return new Result<>(false, StatusCode.ARGERROR, "参数异常");
        }
        Example example = createExample(brand);
        return new Result<>(true, StatusCode.OK, "查询成功", brandMapper.selectByExample(example));
    }

    /**
     * 分页查询
     * @param page     当前页面
     * @param pageSize 每页大小
     * @return 响应数据
     */
    @Override
    public Result<PageInfo<Brand>> findByPager(Integer page, Integer pageSize) {
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        // 生成分页查询对象
        PageHelper.startPage(page, pageSize);
        // 查询集合
        List<Brand> brandList = brandMapper.selectAll();
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(brandList));
    }

    /**
     * 条件分页查询
     * @param brand    条件实体
     * @param page     当前页
     * @param pageSize 每页大小
     * @return 响应数据
     */
    @Override
    public Result<PageInfo<Brand>> findByPagerParam(Brand brand, Integer page, Integer pageSize) {
        if(brand == null || page == null || pageSize == null) {
            return new Result<>(false, StatusCode.ARGERROR, "参数异常");
        }
        // 生成分页查询对象
        PageHelper.startPage(page, pageSize);
        // 搜索数据
        Example example = createExample(brand);
        List<Brand> brandList = brandMapper.selectByExample(example);
        // 封装PageInfo
        // 返回数据
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(brandList));
    }


    /**
     * 增加品牌
     * @param brand 响应数据
     */
    @Override
    public Result<Boolean> add(Brand brand) {
        if(brand == null) {
            return new Result<>(false, StatusCode.ARGERROR, "参数异常");
        }
        int i = brandMapper.insertSelective(brand);
        if(i>0) { return new Result<>(true, StatusCode.OK, "添加成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 编辑品牌
     * @param brand 响应数据
     */
    @Override
    public Result<Boolean> update(Brand brand) {
        if (brand == null) {
            return new Result<>(false, StatusCode.ARGERROR, "参数异常");
        }
        int i = brandMapper.updateByPrimaryKeySelective(brand);
        if(i>0) { return new Result<>(true, StatusCode.OK, "修改成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除品牌
     * @param id 品牌id
     * @return  响应数据
     */
    @Override
    public Result<Boolean> delete(Integer id) {
        if(id == null) {
            return new Result<>(false, StatusCode.ARGERROR, "参数异常");
        }
        int i = brandMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "删除成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 生成查询条件
     * @param brand     品牌实体
     * @return          查询条件 example
     */
    private Example createExample(Brand brand) {
        // 自定义条件搜索对象 Example
        Example example = new Example(Brand.class);
        // 构建条件构造器
        Example.Criteria criteria = example.createCriteria();
        if(brand!= null) {
            if(!StringUtils.isEmpty((brand.getName()))) {
                criteria.andLike("name","%"+brand.getName()+"%");
            }
            if(!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter",brand.getLetter());
            }
        }
        return example;
    }
}
