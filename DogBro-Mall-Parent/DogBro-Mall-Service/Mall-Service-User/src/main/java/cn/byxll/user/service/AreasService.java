package cn.byxll.user.service;


import cn.byxll.user.pojo.Areas;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * 行政区域县区信息 service 接口类
 * @author By-Lin
 */
public interface AreasService {

    /**
     * 添加区域县区信息
     * @param areas     区县信息实体
     * @return          响应数据
     */
    Result<Boolean> add(Areas areas);

    /**
     * 删除Areas
     * @param id        主键id
     * @return          响应数据
     */
    Result<Boolean> delete(String id);

    /**
     * 修改Areas数据
     * @param areas     区县信息实体
     * @return          响应数据
     */
    Result<Boolean> update(Areas areas);


    /**
     * 根据ID查询Areas
     * @param id        主键id
     * @return          响应数据
     */
    Result<Areas> findById(String id);

    /**
     * 查询所有Areas
     * @return      响应数据
     */
    Result<List<Areas>> findAll();

    /**
     * Areas多条件搜索
     * @param areas         条件实体
     * @return              响应数据
     */
    Result<List<Areas>> findList(Areas areas);

    /**
     * Areas分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Areas>> findByPager(Integer page, Integer pageSize);

    /**
     * Areas多条件分页查询
     * @param areas         条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Areas>> findPagerByParam(Areas areas, Integer page, Integer pageSize);

}
