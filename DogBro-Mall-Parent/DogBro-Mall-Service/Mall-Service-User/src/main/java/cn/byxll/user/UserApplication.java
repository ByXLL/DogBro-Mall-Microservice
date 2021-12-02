package cn.byxll.user;

import interceptor.FeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 用户微服务 启动类
 * @author By-Lin
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.byxll.user.dao")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    /**
     * 注入feign拦截器注入到容器中
     * @return
     */
    @Bean
    public FeignInterceptor initFeignInterceptor() {
        return new FeignInterceptor();
    }
}
