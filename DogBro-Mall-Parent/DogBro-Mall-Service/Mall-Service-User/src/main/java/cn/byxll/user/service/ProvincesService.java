package cn.byxll.user.service;

import cn.byxll.user.pojo.Provinces;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * Provinces业务层接口类
 * @author @By-Lin
 */
public interface ProvincesService {

    /**
     * 新增Provinces
     * @param   provinces      Provinces实体
     * @return                 响应数据
     */
    Result<Boolean> add(Provinces provinces);

    /**
     * 通过组件删除Provinces
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(String id);

    /**
     * 修改Provinces数据
     * @param provinces      Provinces实体
     * @return              响应数据
     */
    Result<Boolean> update(Provinces provinces);

    /**
     * 根据ID查询Provinces
     * @param id        主键id
     * @return          响应数据
     */
    Result<Provinces> findById(String id);

    /**
     * 查询所有Provinces
     * @return          响应数据
     */
    Result<List<Provinces>> findAll();

    /**
     * Provinces分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Provinces>> findByPager(Integer page, Integer pageSize);

    /**
     * Provinces条件分页查询
     * @param provinces      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Provinces>> findPagerByParam(Provinces provinces, Integer page, Integer pageSize);
    

    /**
     * Provinces多条件搜索方法
     * @param provinces      条件实体
     * @return              响应数据
     */
    Result<List<Provinces>> findList(Provinces provinces);
}
