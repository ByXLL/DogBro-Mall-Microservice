package cn.byxll.goods.service;

import cn.byxll.goods.pojo.Sku;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * Sku Service接口类
 * @author By-Lin
 */
public interface SkuService {

    /**
     * 新增Sku
     * @param sku 实体
     * @return         响应数据
     */
    Result<Boolean> add(Sku sku);

    /**
     * 删除Sku
     * @param id  主键id
     * @return    响应数据
     */
    Result<Boolean> delete(String id);

    /**
     * 修改Sku数据
     * @param sku  实体
     * @return     响应数据
     */
    Result<Boolean> update(Sku sku);

    /**
     * 根据ID查询Sku
     * @param id    主键id
     * @return      响应数据
     */
    Result<Sku> findById(String id);

    /**
     * Sku分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Sku>> findByPager(Integer page, Integer pageSize);

    /**
     * Sku多条件分页查询
     * @param sku           Sku 实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Sku>> findPagerByParam(Sku sku, Integer page, Integer pageSize);


    /**
     * Sku多条件搜索方法
     * @param sku      实体
     * @return         响应数据
     */
    Result<List<Sku>> findListByParam(Sku sku);

    /**
     * 查询所有Sku
     * @return      响应数据
     */
    Result<List<Sku>> findAll();

}
