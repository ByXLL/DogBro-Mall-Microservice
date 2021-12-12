package cn.byxll.order.mq.queue;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 订单延时关闭队列 配置类
 * 创建另外一个队列，用于订单超时处理，延时30分钟再去触发另外一个关闭订单队列，达到延时的效果
 * @author By-Lin
 */
@Configuration
public class QueueConfig {
    /**
     * 队列一，延时队列，会过期，过期后将数据发给Queue2
     * @return      Queue
     */
    @Bean
    public Queue orderDelayQueue() {
        return QueueBuilder
                .durable("orderDelayQueue")
                // 设置死信交换机 orderDelayQueue队列回过期，过期之后回进入到死信交换机
                .withArgument("x-dead-letter-exchange","orderListenerExchange")
                .withArgument("x-dead-letter-routing-key","orderListenerQueue")
                .build();
    }

    /**
     * 队列二
     * @return
     */
    @Bean(name = "orderListenerQueue")
    public Queue orderListenerQueue() {
        return new Queue("orderDelayQueue",true);
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean(name = "orderListenerExchange")
    public Exchange orderListenerExchange() {
        return new DirectExchange("orderListenerExchange");
    }

    /**
     * 队列二，绑定到交换机
     * @return
     */
    @Bean
    public Binding orderListenerBinding(Queue orderListenerQueue,Exchange orderListenerExchange) {
        return BindingBuilder
                .bind(orderListenerQueue)
                .to(orderListenerExchange)
                .with("orderListenerExchange")
                .noargs();
    }
}
