package cn.byxll.oauth.config;

import cn.byxll.oauth.exception.MyAccessDeniedHandler;
import cn.byxll.oauth.exception.MyAuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;

/**
 * 1、其他资源访问异常处理配置
 * @author By-Lin
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        //自定义资源访问认证异常，没有token，或token错误，使用MyAuthenticationEntryPoint
        resources.authenticationEntryPoint(new MyAuthenticationEntryPoint());
        resources.accessDeniedHandler(new MyAccessDeniedHandler());
    }
}
