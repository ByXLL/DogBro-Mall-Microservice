package cn.byxll.seckill.mq;

import cn.byxll.seckill.service.impl.SeckillOrderServiceImpl;
import com.alibaba.fastjson.JSON;
import entity.SeckillStatus;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 秒杀商品支付过期 MQ 监听器
 * @author By-Lin
 */
@Component
@RabbitListener(queues = "seckillCancelQueue")
public class DelaySecKillMessageListener {
    private final SeckillOrderServiceImpl seckillOrderService;
    private final RedisTemplate redisTemplate;

    public DelaySecKillMessageListener(SeckillOrderServiceImpl seckillOrderService, RedisTemplate redisTemplate) {
        this.seckillOrderService = seckillOrderService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 消息处理器
     * @param message       消息
     */
    @RabbitHandler
    public void getMessage(String message) {
        System.out.println("监听到->秒杀订单超时未支付");
        try{
            SeckillStatus seckillStatus = JSON.parseObject(message, SeckillStatus.class);
            // 如果当前redis中没有用户排队信息，则表明该订单已经处理，如果有用户排队信息，则表示未完成支付，关闭订单，回滚库存
            Object userQueueStatus = redisTemplate.boundHashOps("UserQueueStatus").get(seckillStatus.getUserName());
            if(userQueueStatus!=null) {
                // 删除订单
                seckillOrderService.deleteOrderByUserName(seckillStatus.getUserName());
                // 关闭微信支付
                // todo
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将支付信息转成map
    }
}
