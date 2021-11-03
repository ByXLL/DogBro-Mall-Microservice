package cn.byxll.user.service.impl;

import cn.byxll.user.dao.OauthClientDetailsMapper;
import cn.byxll.user.pojo.OauthClientDetails;
import cn.byxll.user.service.OauthClientDetailsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * OauthClientDetails业务层接口实现类
 * @author By-Lin
 */
@Service
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService {

    private final OauthClientDetailsMapper oauthClientDetailsMapper;

    public OauthClientDetailsServiceImpl(OauthClientDetailsMapper oauthClientDetailsMapper) {
        this.oauthClientDetailsMapper = oauthClientDetailsMapper;
    }

    /**
     * 新增OauthClientDetails
     * @param oauthClientDetails      OauthClientDetails实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(OauthClientDetails oauthClientDetails){
        if(oauthClientDetails == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = oauthClientDetailsMapper.insert(oauthClientDetails);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(String id){
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = oauthClientDetailsMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改OauthClientDetails
     * @param oauthClientDetails      OauthClientDetails实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(OauthClientDetails oauthClientDetails){
        if(oauthClientDetails == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = oauthClientDetailsMapper.updateByPrimaryKey(oauthClientDetails);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询OauthClientDetails
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<OauthClientDetails> findById(String id) {
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        OauthClientDetails oauthClientDetails = oauthClientDetailsMapper.selectByPrimaryKey(id);
        if(oauthClientDetails != null) { return new Result<>(true, StatusCode.OK, "查询成功", oauthClientDetails); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有OauthClientDetails
     * @return      响应数据
     */
    @Override
    public Result<List<OauthClientDetails>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", oauthClientDetailsMapper.selectAll());
    }

    /**
     * OauthClientDetails分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<OauthClientDetails>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(oauthClientDetailsMapper.selectAll()));
    }


    /**
     * OauthClientDetails条件分页查询
     * @param oauthClientDetails      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<OauthClientDetails>> findPagerByParam(OauthClientDetails oauthClientDetails, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(oauthClientDetails);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<OauthClientDetails>(oauthClientDetailsMapper.selectByExample(example)));
    }

    /**
     * OauthClientDetails多条件搜索方法
     * @param oauthClientDetails      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<OauthClientDetails>> findList(OauthClientDetails oauthClientDetails){
        if(oauthClientDetails == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(oauthClientDetails);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<OauthClientDetails>(oauthClientDetailsMapper.selectByExample(example)));
    }


    /**
     * OauthClientDetails构建查询对象
     * @param oauthClientDetails      OauthClientDetails实体
     * @return              查询对象
     */
    private Example createExample(OauthClientDetails oauthClientDetails){
        Example example = new Example(OauthClientDetails.class);
        Example.Criteria criteria = example.createCriteria();
        if(oauthClientDetails!=null){
            // 客户端ID，主要用于标识对应的应用
            if(!StringUtils.isEmpty(oauthClientDetails.getClientId())){
                criteria.andEqualTo("clientId",oauthClientDetails.getClientId());
            }
            // 
            if(!StringUtils.isEmpty(oauthClientDetails.getResourceIds())){
                criteria.andEqualTo("resourceIds",oauthClientDetails.getResourceIds());
            }
            // 客户端秘钥，BCryptPasswordEncoder加密
            if(!StringUtils.isEmpty(oauthClientDetails.getClientSecret())){
                criteria.andEqualTo("clientSecret",oauthClientDetails.getClientSecret());
            }
            // 对应的范围
            if(!StringUtils.isEmpty(oauthClientDetails.getScope())){
                criteria.andEqualTo("scope",oauthClientDetails.getScope());
            }
            // 认证模式
            if(!StringUtils.isEmpty(oauthClientDetails.getAuthorizedGrantTypes())){
                criteria.andEqualTo("authorizedGrantTypes",oauthClientDetails.getAuthorizedGrantTypes());
            }
            // 认证后重定向地址
            if(!StringUtils.isEmpty(oauthClientDetails.getWebServerRedirectUri())){
                criteria.andEqualTo("webServerRedirectUri",oauthClientDetails.getWebServerRedirectUri());
            }
            // 
            if(!StringUtils.isEmpty(oauthClientDetails.getAuthorities())){
                criteria.andEqualTo("authorities",oauthClientDetails.getAuthorities());
            }
            // 令牌有效期
            if(!StringUtils.isEmpty(oauthClientDetails.getAccessTokenValidity())){
                criteria.andEqualTo("accessTokenValidity",oauthClientDetails.getAccessTokenValidity());
            }
            // 令牌刷新周期
            if(!StringUtils.isEmpty(oauthClientDetails.getRefreshTokenValidity())){
                criteria.andEqualTo("refreshTokenValidity",oauthClientDetails.getRefreshTokenValidity());
            }
            // 
            if(!StringUtils.isEmpty(oauthClientDetails.getAdditionalInformation())){
                criteria.andEqualTo("additionalInformation",oauthClientDetails.getAdditionalInformation());
            }
            // 
            if(!StringUtils.isEmpty(oauthClientDetails.getAutoapprove())){
                criteria.andEqualTo("autoapprove",oauthClientDetails.getAutoapprove());
            }
        }
        return example;
    }

}
