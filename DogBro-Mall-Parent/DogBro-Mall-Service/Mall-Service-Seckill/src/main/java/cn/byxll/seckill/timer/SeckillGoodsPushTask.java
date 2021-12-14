package cn.byxll.seckill.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * 定时将秒杀商品存入到Redis缓存
 * @author By-Lin
 */
@Component
public class SeckillGoodsPushTask {
    /****
     * 每30秒执行一次
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void loadGoodsPushRedis(){
        System.out.println("task demo");
    }
}
