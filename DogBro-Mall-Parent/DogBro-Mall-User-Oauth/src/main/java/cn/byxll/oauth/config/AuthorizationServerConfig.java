package cn.byxll.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.security.KeyPair;


/**
 * 授权服务器配置类
 */
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /** 数据源，用于从数据库获取数据进行认证操作，测试可以从内存中获取 */
    @Autowired
    private DataSource dataSource;

    /** jwt令牌转换器 **/
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /** SpringSecurity 用户自定义授权认证类 */
    @Autowired
    UserDetailsService userDetailsService;

    /** 授权认证管理器 */
    @Autowired
    AuthenticationManager authenticationManager;

    /** 令牌持久化存储接口 */
    @Autowired
    TokenStore tokenStore;

    @Autowired
    private CustomUserAuthenticationConverter customUserAuthenticationConverter;


    /**
     * 重写客户端信息配置
     * @param clients       客户端配置信息实体
     * @throws Exception    异常
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 客户端id
                .withClient("DogBro")
                // 秘钥
                .secret("Aa123456")
                // 重定向地址
                .redirectUris("http://localhost")
                // 访问令牌有效期
                .accessTokenValiditySeconds(3600)
                // 刷新令牌有效期
                .refreshTokenValiditySeconds(3600)
                .authorizedGrantTypes(
                        // 根据授权码生成令牌
                        "authorization_code",
                        // 客户端认证
                        "client_credentials",
                        // 刷新令牌
                        "refresh_token",
                        // 密码方式认证
                        "password")
                // 客户端范围，名称自定义，必填
                .scopes("app");
    }

    /**
     * 重写授权服务器端点配置
     * @param endpoints     服务器端点配置实体
     * @throws Exception    抛出异常
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(jwtAccessTokenConverter)
                // 认证管理器
                .authenticationManager(authenticationManager)
                // 令牌存储
                .tokenStore(tokenStore)
                // 用户信息service
                .userDetailsService(userDetailsService);
    }

    /**
     * 重写授权服务器的安全配置
     * @param oauthServer       服务器的安全配置类
     * @throws Exception        抛出异常
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients()
                .passwordEncoder(new BCryptPasswordEncoder())
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }


    // 读取密钥的配置
    @Bean("keyProp")
    public KeyProperties keyProperties(){
        return new KeyProperties();
    }

    @Resource(name = "keyProp")
    private KeyProperties keyProperties;

    // 客户端配置
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    @Autowired
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /**
     * JWT令牌转换器
     * @param customUserAuthenticationConverter
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(CustomUserAuthenticationConverter customUserAuthenticationConverter) {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(
                // 证书路径 dogbro.jks
                keyProperties.getKeyStore().getLocation(),
                // 证书秘钥 Aa123456
                keyProperties.getKeyStore().getSecret().toCharArray()).getKeyPair(
                        // 证书别名 DogBro
                        keyProperties.getKeyStore().getAlias(),
                        // 证书密码 Aa123456
                        keyProperties.getKeyStore().getPassword().toCharArray());
        converter.setKeyPair(keyPair);
        //配置自定义的CustomUserAuthenticationConverter
        DefaultAccessTokenConverter accessTokenConverter = (DefaultAccessTokenConverter) converter.getAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(customUserAuthenticationConverter);
        return converter;
    }
}

