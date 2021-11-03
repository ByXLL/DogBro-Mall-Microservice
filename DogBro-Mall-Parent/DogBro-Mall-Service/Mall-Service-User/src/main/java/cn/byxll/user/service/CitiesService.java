package cn.byxll.user.service;

import cn.byxll.user.pojo.Cities;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * Cities业务层接口类
 * @author @By-Lin
 */
public interface CitiesService {

    /**
     * 新增Cities
     * @param   cities      Cities实体
     * @return                 响应数据
     */
    Result<Boolean> add(Cities cities);

    /**
     * 通过组件删除Cities
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(String id);

    /**
     * 修改Cities数据
     * @param cities      Cities实体
     * @return              响应数据
     */
    Result<Boolean> update(Cities cities);

    /**
     * 根据ID查询Cities
     * @param id        主键id
     * @return          响应数据
     */
    Result<Cities> findById(String id);

    /**
     * 查询所有Cities
     * @return          响应数据
     */
    Result<List<Cities>> findAll();

    /**
     * Cities分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Cities>> findByPager(Integer page, Integer pageSize);

    /**
     * Cities条件分页查询
     * @param cities      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<Cities>> findPagerByParam(Cities cities, Integer page, Integer pageSize);
    

    /**
     * Cities多条件搜索方法
     * @param cities      条件实体
     * @return              响应数据
     */
    Result<List<Cities>> findList(Cities cities);
}
