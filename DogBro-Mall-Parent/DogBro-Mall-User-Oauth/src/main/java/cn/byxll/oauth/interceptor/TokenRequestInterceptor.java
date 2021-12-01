package cn.byxll.oauth.interceptor;

import cn.byxll.oauth.util.AdminToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * feign服务间调用拦截器
 * @author By-Lin
 */
@Configuration
public class TokenRequestInterceptor implements RequestInterceptor {
    /**
     * Called for every request. Add data using methods on the supplied {@link RequestTemplate}.
     * 在feign执行之前拦截
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        /**
         * 从数据库加载查询用户信息
         * 1、没有令牌，Feign调用之前，生成令牌(token)
         * 2、Feign调用之前，令牌需要携带过去
         * 3、Feign调用之前，令牌存放到Header中
         * 4、请求->Feign调用->拦截器RequestInterceptor->Feign调用之前执行拦截
         */
        String adminToken = AdminToken.getAdminToken();
        template.header("Authorization","bearer "+adminToken);
    }
}
