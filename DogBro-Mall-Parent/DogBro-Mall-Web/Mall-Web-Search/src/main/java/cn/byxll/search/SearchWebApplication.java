package cn.byxll.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 商品搜索web页面微服务启动类
 * @author By-Lin
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "cn.byxll.search.feign")
public class SearchWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchWebApplication.class,args);
    }
}
