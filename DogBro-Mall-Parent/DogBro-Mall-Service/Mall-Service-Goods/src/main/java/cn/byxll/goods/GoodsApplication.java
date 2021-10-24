package cn.byxll.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * goods service 启动类
 * 并开启 EurekaClient 和通用mapper包扫描
 * @author By-Lin
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"cn.byxll.goods.dao"})
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class,args);
    }
}
