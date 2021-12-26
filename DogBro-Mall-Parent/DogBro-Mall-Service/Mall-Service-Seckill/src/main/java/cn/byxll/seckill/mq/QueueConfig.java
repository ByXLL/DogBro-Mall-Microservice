package cn.byxll.seckill.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 秒杀订单超时延时队列
 * @author By-Lin
 */
@Configuration
public class QueueConfig {

    /**
     * 延时超时队列->负责数据暂时存储
     * @return
     */
    @Bean
    public Queue delaySeckillQueue() {
        return QueueBuilder.durable("delaySeckillQueue")
                .withArgument("x-dead-letter-exchange","seckillExchange")
                .withArgument("x-dead-letter-routing-key","seckillCancelQueue")
                .build();
    }

    /**
     * 秒杀商品超时取消消息队列
     * 真正监听的消息队列
     * @return
     */
    @Bean
    public Queue seckillCancelQueue() {
        return new Queue("seckillCancelQueue");
    }

    /**
     * 交换机
     * @return
     */
    @Bean
    public Exchange seckillExchange() {
        return new DirectExchange("seckillExchange");
    }

    /**
     * 绑定到交换机
     * @return
     */
    @Bean
    public Binding seckillQueueBinding(Queue seckillCancelQueue, Exchange seckillExchange) {
        return BindingBuilder
                // 绑定的队列
                .bind(seckillCancelQueue)
                // 要去的交换机
                .to(seckillExchange)
                // 路由
                .with("seckillCancelQueue")
                .noargs();
    }
}
