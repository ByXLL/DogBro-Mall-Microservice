package cn.byxll.service;

import cn.byxll.goods.pojo.Category;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * 商品分类 service 接口
 * @author By-Lin
 */
public interface CategoryService {
    /**
     * 添加商品分类
     * @param category      商品分类实体
     * @return              响应数据
     */
    Result<Boolean> add(Category category);

    /**
     * 删除商品分类
     * @param id        商品分类id
     * @return          响应数据
     */
    Result<Boolean> delete(Integer id);

    /**
     * 编辑商品分类
     * @param category      商品分类实体
     * @return              响应数据
     */
    Result<Boolean> update(Category category);

    /**
     * 通过id查询
     * @param id        商品分类
     * @return          响应数据
     */
    Result<Category> findById(Integer id);

    /**
     * 分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Category>> findByPager(Integer page, Integer pageSize);

    /**
     * 按条件分页查询
     * @param category      商品分类实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Category>> findPagerByParam(Category category, Integer page, Integer pageSize);

    /**
     * 根据父节点查询
     * @param pId       父节点id
     * @return          响应数据
     */
    Result<List<Category>> findByParentId(Integer pId);

    /**
     * 根据模板id查询集合
     * @param tId       模板id
     * @return          响应数据
     */
    Result<List<Category>> findByTemplateId(Integer tId);

    /**
     * 查询所有分类
     * @return      响应数据
     */
    Result<List<Category>> findAll();
}
