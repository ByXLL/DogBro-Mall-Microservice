package cn.byxll.service.impl;

import cn.byxll.dao.BrandMapper;
import cn.byxll.goods.pojo.Brand;
import cn.byxll.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
     * @return 品牌 list
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * 通过id查询一个品牌
     *
     * @param id 品牌id
     * @return 品牌实体
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过条件查询品牌
     *
     * @param brand 品牌实体
     * @return 品牌集合
     */
    @Override
    public List<Brand> findByEntity(Brand brand) {
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    /**
     * 分页查询
     *
     * @param page     当前页面
     * @param pageSize 每页大小
     * @return 分页实体
     */
    @Override
    public PageInfo<Brand> findByPager(Integer page, Integer pageSize) {
        // 生成分页查询对象
        PageHelper.startPage(page, pageSize);
        // 查询集合
        List<Brand> brandList = brandMapper.selectAll();
        return new PageInfo<>(brandList);
    }

    /**
     * 条件分页查询
     *
     * @param brand    条件实体
     * @param page     当前页
     * @param pageSize 每页大小
     * @return 分页实体
     */
    @Override
    public PageInfo<Brand> findByPagerEntity(Brand brand, Integer page, Integer pageSize) {
        // 生成分页查询对象
        PageHelper.startPage(page, pageSize);
        // 搜索数据
        Example example = createExample(brand);
        List<Brand> brandList = brandMapper.selectByExample(example);
        // 封装PageInfo
        return new PageInfo<>(brandList);
    }


    /**
     * 增加品牌
     * @param brand 品牌实体
     */
    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    /**
     * 编辑品牌
     *
     * @param brand 品牌实体
     */
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 删除品牌
     *
     * @param id 品牌id
     */
    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
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
