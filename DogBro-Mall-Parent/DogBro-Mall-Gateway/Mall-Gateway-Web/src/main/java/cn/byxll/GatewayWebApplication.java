package cn.byxll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * web 微服务网关启动类
 * @author By-Lin
 */
@EnableEurekaClient
@SpringBootApplication
public class GatewayWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayWebApplication.class,args);
    }

    /**
     * 创建用户唯一标识，使用ip作为用户唯一标识，来根据ip进行限流操作
     * 以bean的方式注入到容器中，然后在yml中的限流中使用该标识符
     * @return          唯一标识
     */
    @Bean(name = "ipKeyResolver")
    public KeyResolver userKeyResolver() {
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                String ip = Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostString();
                return Mono.just(ip);
            }
        };
    }
}
