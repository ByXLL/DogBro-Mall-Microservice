package cn.byxll.service;

import cn.byxll.goods.pojo.CategoryBrand;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * CategoryBrand Service接口类
 * @author By-Lin
 */
public interface CategoryBrandService {

    /**
     * 新增CategoryBrand
     * @param categoryBrand 实体
     * @return         响应数据
     */
    Result<Boolean> add(CategoryBrand categoryBrand);

    /**
     * 删除CategoryBrand
     * @param id  主键id
     * @return    响应数据
     */
    Result<Boolean> delete(Integer id);

    /**
     * 修改CategoryBrand数据
     * @param categoryBrand  实体
     * @return          响应数据
     */
    Result<Boolean> update(CategoryBrand categoryBrand);

    /**
     * 根据ID查询CategoryBrand
     * @param id    主键id
     * @return      响应数据
     */
    Result<CategoryBrand> findById(Integer id);

    /**
     * CategoryBrand 分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<CategoryBrand>> findByPager(Integer page, Integer pageSize);

    /**
     * CategoryBrand 多条件分页查询
     * @param categoryBrand         CategoryBrand 实体
     * @param page                  当前页码
     * @param pageSize              每页大小
     * @return                      响应数据
     */
    Result<PageInfo<CategoryBrand>> findPagerByParam(CategoryBrand categoryBrand, Integer page, Integer pageSize);

    /**
     * CategoryBrand多条件搜索方法
     * @param categoryBrand      实体
     * @return                   响应数据
     */
    Result<List<CategoryBrand>> findListByParam(CategoryBrand categoryBrand);

    /**
     * 查询所有CategoryBrand
     * @return      响应数据
     */
    Result<List<CategoryBrand>> findAll();

}
