package cn.byxll.user.service;

import cn.byxll.user.pojo.OauthClientDetails;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * OauthClientDetails业务层接口类
 * @author @By-Lin
 */
public interface OauthClientDetailsService {

    /**
     * 新增OauthClientDetails
     * @param   oauthClientDetails      OauthClientDetails实体
     * @return                 响应数据
     */
    Result<Boolean> add(OauthClientDetails oauthClientDetails);

    /**
     * 通过组件删除OauthClientDetails
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(String id);

    /**
     * 修改OauthClientDetails数据
     * @param oauthClientDetails      OauthClientDetails实体
     * @return              响应数据
     */
    Result<Boolean> update(OauthClientDetails oauthClientDetails);

    /**
     * 根据ID查询OauthClientDetails
     * @param id        主键id
     * @return          响应数据
     */
    Result<OauthClientDetails> findById(String id);

    /**
     * 查询所有OauthClientDetails
     * @return          响应数据
     */
    Result<List<OauthClientDetails>> findAll();

    /**
     * OauthClientDetails分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<OauthClientDetails>> findByPager(Integer page, Integer pageSize);

    /**
     * OauthClientDetails条件分页查询
     * @param oauthClientDetails      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<OauthClientDetails>> findPagerByParam(OauthClientDetails oauthClientDetails, Integer page, Integer pageSize);
    

    /**
     * OauthClientDetails多条件搜索方法
     * @param oauthClientDetails      条件实体
     * @return              响应数据
     */
    Result<List<OauthClientDetails>> findList(OauthClientDetails oauthClientDetails);
}
