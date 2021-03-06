package cn.byxll.order;

import interceptor.FeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * 订单微服务启动类
 * @author By-Lin
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients( value = {"cn.byxll.goods.feign", "cn.byxll.order.feign"})
@MapperScan(basePackages = {"cn.byxll.order.dao"})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
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