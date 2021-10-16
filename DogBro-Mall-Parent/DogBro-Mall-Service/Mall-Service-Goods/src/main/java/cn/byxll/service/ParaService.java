package cn.byxll.service;

import cn.byxll.goods.pojo.Para;
import com.github.pagehelper.PageInfo;
import entity.Result;

/**
 * 参数 service 接口
 * @author By-Lin
 */
public interface ParaService {
    /**
     * 添加参数
     * @param para      参数实体
     * @return          响应数据
     */
    Result<Boolean> add(Para para);

    /**
     * 删除参数
     * @param id        参数id
     * @return          响应数据
     */
    Result<Boolean> delete(Integer id);

    /**
     * 修改参数
     * @param para      参数实体
     * @return          响应数据
     */
    Result<Boolean> update(Para para);

    /**
     * 通过id查询参数
     * @param id        参数id
     * @return          响应数据
     */
    Result<Para> findById(Integer id);

    /**
     * 分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Para>> findByPager(Integer page, Integer pageSize);

    /**
     * 分页条件查询
     * @param para          商品参数实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Para>> findPagerByParam(Para para, Integer page, Integer pageSize);
}
