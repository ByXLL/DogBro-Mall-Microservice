package interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

/**
 * feign服务间调用拦截器
 * @author By-Lin
 */
public class FeignInterceptor implements RequestInterceptor {
    /**
     * Called for every request. Add data using methods on the supplied {@link RequestTemplate}.
     * 在feign执行之前拦截
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        /**
         * 获取当前登录的用户的token
         * 在feign调用的时候，将token传递下去
         *
         * RequestContextHolder.getRequestAttributes()
         * 记录的是用户当前请求的时候对应线程的数据
         * feign.hystrix.enabled: true  开启熔断策略 默认使用的是线程池，就不是用户的那个线程了，将获取不到
         * 需要将熔断策略换成信号量隔离，此时就不会启用线程池
         */

        // 记录了当前用户请求的所有数据，包含请求头和请求参数
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(requestAttributes == null) { throw new ArithmeticException("请求头缺失"); }
        // 获取请求头所有名字
        Enumeration<String> headerNames = requestAttributes.getRequest().getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerKey = headerNames.nextElement();
            String headerValue = requestAttributes.getRequest().getHeader(headerKey);
            // 将请求头信息封装到头中，使用feign调用的时候，会传递给下一个微服务
            template.header(headerKey,headerValue);
        }
    }
}
