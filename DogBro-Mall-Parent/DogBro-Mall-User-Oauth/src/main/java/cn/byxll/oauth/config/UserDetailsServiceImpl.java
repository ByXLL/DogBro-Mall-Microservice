package cn.byxll.oauth.config;
import cn.byxll.oauth.exception.CustomOauthException;
import cn.byxll.oauth.exception.CustomOauthExceptionSerializer;
import cn.byxll.oauth.util.UserJwt;
import cn.byxll.user.feign.UserFeign;
import entity.Result;
import exception.UserNoFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 自定义授权认证类
 * @author By-Lin
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserFeign userFeign;

    final ClientDetailsService clientDetailsService;

    public UserDetailsServiceImpl(UserFeign userFeign, ClientDetailsService clientDetailsService) {
        this.userFeign = userFeign;
        this.clientDetailsService = clientDetailsService;
    }

    /**
     * 重写授权认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        // 客户端信息验证
        // 取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication==null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if(clientDetails!=null){
                //秘钥
                String clientSecret = clientDetails.getClientSecret();
                //静态方式
//                return new User(
//                        // 客户端id
//                        username,
//                        // 客户端密钥
//                        new BCryptPasswordEncoder().encode(clientSecret),
//                        AuthorityUtils.commaSeparatedStringToAuthorityList(""));
                //数据库查找方式
                return new User(
                        // 客户端id
                        username,
                        // 客户端密钥
                        clientSecret,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(String.valueOf(clientDetails.getAuthorities())));
            }
        }
        // 用户信息验证
        if (StringUtils.isEmpty(username)) { throw new UsernameNotFoundException("用户名为空"); }
        // 从数据库加载查询用户信息
        Result<cn.byxll.user.pojo.User> userResult = userFeign.findById(username);
        if(userResult == null || !userResult.isFlag() || userResult.getData()== null) {return null;}

        // 根据用户名查询用户信息
        // String pwd = new BCryptPasswordEncoder().encode("Aa123456");
        String pwd = userResult.getData().getPassword();
        // 创建User对象
        // 指定用户的角色信息  模拟设计 后面通过数据库设计补充
        String permissions = "user,admin";
        UserJwt userDetails = new UserJwt(username,pwd,AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        userDetails.setCompany("Tencent");
        return userDetails;
//        return checkUserInfo(username);

    }

    /**
     * 用户信息验证
     * 调用user微服务校验用户信息
     * @param username      用户名
     * @return              生成的用户详情token信息
     */
    private UserDetails checkUserInfo(String username) {
        // 用户信息验证
        if (StringUtils.isEmpty(username)) { throw new UsernameNotFoundException("用户名为空"); }
        // 从数据库加载查询用户信息
        Result<cn.byxll.user.pojo.User> userResult = userFeign.findById(username);
        if(userResult == null || !userResult.isFlag() || userResult.getData()== null) {return null;}

        // 根据用户名查询用户信息
        // String pwd = new BCryptPasswordEncoder().encode("Aa123456");
        String pwd = userResult.getData().getPassword();
        // 创建User对象
        // 指定用户的角色信息  模拟设计 后面通过数据库设计补充
        String permissions = "user,admin";
        UserJwt userDetails = new UserJwt(username,pwd,AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        userDetails.setCompany("Tencent");
        return userDetails;
    }
}
