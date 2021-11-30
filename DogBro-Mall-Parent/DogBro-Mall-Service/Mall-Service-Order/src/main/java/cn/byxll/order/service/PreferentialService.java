package cn.byxll.order.service;

import cn.byxll.order.pojo.Preferential;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * Preferential业务层接口类
 * @author @By-Lin
 */
public interface PreferentialService {

    /**
     * 新增Preferential
     * @param   preferential      Preferential实体
     * @return                 响应数据
     */
    Result<Boolean> add(Preferential preferential);

    /**
     * 通过组件删除Preferential
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(Integer id);

    /**
     * 修改Preferential数据
     * @param preferential      Preferential实体
     * @return              响应数据
     */
    Result<Boolean> update(Preferential preferential);

    /**
     * 根据ID查询Preferential
     * @param id        主键id
     * @return          响应数据
     */
    Result<Preferential> findById(Integer id);

    /**
     * 查询所有Preferential
     * @return          响应数据
     */
    Result<List<Preferential>> findAll();

    /**
     * Preferential分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Preferential>> findByPager(Integer page, Integer pageSize);

    /**
     * Preferential条件分页查询
     * @param preferential      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Preferential>> findPagerByParam(Preferential preferential, Integer page, Integer pageSize);
    

    /**
     * Preferential多条件搜索方法
     * @param preferential      条件实体
     * @return              响应数据
     */
    Result<List<Preferential>> findList(Preferential preferential);
}
