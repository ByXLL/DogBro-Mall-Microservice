package cn.byxll.oauth.config;

import cn.byxll.oauth.exception.MyWebResponseExceptionTranslator;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * 认证服务器处理配置
 * /oauth/token 异常处理
 * @author By-Lin
 */
@Configuration
@EnableAuthorizationServer
public class OAuthSecurityConfig extends AuthorizationServerConfigurerAdapter {
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //自定义 /oauth/token 异常处理
        endpoints.exceptionTranslator(new MyWebResponseExceptionTranslator());
    }
}
