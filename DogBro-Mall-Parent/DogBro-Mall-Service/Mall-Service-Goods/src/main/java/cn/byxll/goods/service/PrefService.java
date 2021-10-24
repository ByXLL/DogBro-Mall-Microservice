package cn.byxll.goods.service;

import cn.byxll.goods.pojo.Pref;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * Pref Service接口类
 * @author By-Lin
 */
public interface PrefService {

    /**
     * 新增Pref
     * @param pref 实体
     * @return         响应数据
     */
    Result<Boolean> add(Pref pref);

    /**
     * 删除Pref
     * @param id  主键id
     * @return    响应数据
     */
    Result<Boolean> delete(Integer id);

    /**
     * 修改Pref数据
     * @param pref  实体
     * @return          响应数据
     */
    Result<Boolean> update(Pref pref);

    /**
     * 根据ID查询Pref
     * @param id    主键id
     * @return      响应数据
     */
    Result<Pref> findById(Integer id);

    /**
     * Pref分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Pref>> findByPager(Integer page, Integer pageSize);

    /**
     * Pref多条件分页查询
     * @param pref          Pref 实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Pref>> findPagerByParam(Pref pref, Integer page, Integer pageSize);


    /**
     * Pref多条件搜索方法
     * @param pref      实体
     * @return          响应数据
     */
    Result<List<Pref>> findListByParam(Pref pref);

    /**
     * 查询所有Pref
     * @return      响应数据
     */
    Result<List<Pref>> findAll();

}
