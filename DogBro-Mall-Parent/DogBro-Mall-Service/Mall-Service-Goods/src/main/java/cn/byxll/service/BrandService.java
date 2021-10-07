package cn.byxll.service;

import cn.byxll.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * 品牌service 接口类
 * @author By-Lin
 */
public interface BrandService {
    /**
     * 查询所有品牌
     * @return      品牌 list
     */
    List<Brand> findAll();

    /**
     * 通过id查询一个品牌
     * @param id        品牌id
     * @return          品牌实体
     */
    Brand findById(Integer id);

    /**
     * 通过条件查询品牌
     * @param brand     品牌实体
     * @return          品牌集合
     */
    List<Brand> findByEntity(Brand brand);

    /**
     * 分页查询
     * @param page      当前页面
     * @param pageSize  每页大小
     * @return          分页实体
     */
    PageInfo<Brand> findByPager(Integer page, Integer pageSize);

    /**
     * 条件分页查询
     * @param brand     条件实体
     * @param page      当前页
     * @param pageSize  每页大小
     * @return          分页实体
     */
    PageInfo<Brand> findByPagerEntity(Brand brand, Integer page, Integer pageSize);

    /**
     * 增加品牌
     * @param brand     品牌实体
     */
    void add(Brand brand);

    /**
     * 编辑品牌
     * @param brand     品牌实体
     */
    void update(Brand brand);

    /**
     * 删除品牌
     * @param id        品牌id
     */
    void delete(Integer id);
}
