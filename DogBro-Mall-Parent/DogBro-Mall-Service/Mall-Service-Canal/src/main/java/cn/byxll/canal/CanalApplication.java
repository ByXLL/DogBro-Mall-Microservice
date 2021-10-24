package cn.byxll.canal;

import org.springframework.boot.SpringApplication;

/**
 * Canal 微服务启动类
 * @author By-Lin
 */
//@EnableEurekaClient
//@EnableCanalClient
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class,args);
    }
}
