package cn.byxll.service;

import cn.byxll.goods.pojo.Spec;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * @author By-Lin
 */
public interface SpecService {
    /**
     * 添加规格
     * @param spec      规格实体
     * @return          响应数据
     */
    Result<Boolean> add(Spec spec);

    /**
     * 删除规格
     * @param id        规格id
     * @return          响应数据
     */
    Result<Boolean> delete(Integer id);

    /**
     * 修改规格
     * @param spec      规格实体
     * @return          响应数据
     */
    Result<Boolean> update(Spec spec);

    /**
     * 通过id查询
     * @param id        规格id
     * @return          响应数据
     */
    Result<Spec> findById(Integer id);

    /**
     * 查询所有规格
     * @return      响应数据
     */
    Result<List<Spec>> findAll();

    /**
     * 分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Spec>> findByPager(Integer page, Integer pageSize);

    /**
     * 分页条件查询
     * @param spec          规格实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Spec>> findPagerByParam(Spec spec, Integer page, Integer pageSize);
}
