package cn.byxll.filter;

import cn.byxll.utils.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器
 * 实现用户权限校验
 * @author By-Lin
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    /** 令牌头名字 */
    private static final String AUTHORIZE_TOKEN = "Authorization";

    /**
     * 全局拦截
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 获取用户信息 默认从请求头中获取
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        // 判断token是否存在请求头中
        boolean isInHeader = true;
        if(StringUtils.isEmpty(token)) {
            // 请求头中获取不到，从参数中获取
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            isInHeader = false;
        }
        if(StringUtils.isEmpty(token)) {
            // 参数中获取不到，从cookies中获取
            HttpCookie cookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if(cookie != null) {
                token = cookie.getValue();
            }
        }
        // 如果没有令牌，则拦截
        if(StringUtils.isEmpty(token)) {
            // 设置 http 状态码 401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 响应空数据
            return response.setComplete();
        }
        // 如果有token则校验
        try {
            JwtUtil.parseJWT(token);
        } catch (Exception e) {
            // token 无效拦截
            // 设置 http 状态码 401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 响应空数据
            return response.setComplete();
        }

        // 将token封装到头文件中 用于将
        request.mutate().header(AUTHORIZE_TOKEN,token);

        // token 校验通过 放行
        return chain.filter(exchange);
    }

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     * 排序，越小越先执行
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
