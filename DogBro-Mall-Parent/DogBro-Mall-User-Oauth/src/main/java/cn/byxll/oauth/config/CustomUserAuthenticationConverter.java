package cn.byxll.oauth.config;

import cn.byxll.oauth.util.UserJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义用户身份验证转换器
 * @author  By-Lin
 */
@Component
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    final UserDetailsService userDetailsService;

    public CustomUserAuthenticationConverter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 重写 security 默认的权限信息转换器 改成我们自定义的jwt载体信息
     * @param authentication        当前用户的验证信息
     * @return                      自定义的信息
     */
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        HashMap<String,Object> response = new HashMap<>(16);
        String name = authentication.getName();
        response.put("username", name);

        Object principal = authentication.getPrincipal();
        UserJwt userJwt = null;
        if(principal instanceof  UserJwt){
            userJwt = (UserJwt) principal;
        }else{
            //refresh_token默认不去调用userdetailService获取用户信息，这里我们手动去调用，得到 UserJwt
            UserDetails userDetails = userDetailsService.loadUserByUsername(name);
            userJwt = (UserJwt) userDetails;
        }
        response.put("name", userJwt.getName());
        response.put("id", userJwt.getId());
        response.put("company",userJwt.getCompany());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }

}
