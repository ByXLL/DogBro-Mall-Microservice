package cn.byxll.seckill.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 秒杀商品支付 MQ 监听器
 * @author By-Lin
 */
@Component
@RabbitListener(queues = "${mq.pay.queue.seckillOrder}")
public class SecKillMessageListener {

    /**
     * 消息处理器
     * @param message       消息
     */
    @RabbitHandler
    public void getMessage(String message) {
        System.out.println("监听到->秒杀订单支付成功");
    }
}
