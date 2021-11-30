package cn.byxll.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * 订单微服务启动类
 * @author By-Lin
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"cn.byxll.order.dao"})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}