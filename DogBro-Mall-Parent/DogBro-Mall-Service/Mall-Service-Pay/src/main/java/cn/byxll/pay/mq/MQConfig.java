package cn.byxll.pay.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

/**
 * MQ配置类
 * @author By-Lin
 */
@Configuration
public class MQConfig {
    /**
     * 读取配置文件中的对象
     */
    private final Environment env;

    public MQConfig(Environment env) {
        this.env = env;
    }

    /**
     * 创建普通订单队列
     * @return
     */
//    @Bean(name = "queueOrder")
    @Bean
    public Queue orderQueue(){
        return new Queue(Objects.requireNonNull(env.getProperty("mq.pay.queue.order")), true);
    }

    /**
     * 创建普通订单DirectExchange交换机
     * @return
     */
    @Bean
    public DirectExchange orderExchange(){
        return new DirectExchange(env.getProperty("mq.pay.exchange.order"), true,false);
    }


    /**
     * 创建普通订单队列绑定到交换机上
     * @return
     */
    @Bean
    public Binding orderExchangeBinding(){
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(env.getProperty("mq.pay.routing.key"));
    }



    /**
     * 创建秒杀订单队列
     * @return
     */
    @Bean
    public Queue seckillOrderQueue(){
        return new Queue(Objects.requireNonNull(env.getProperty("mq.pay.queue.seckillOrder")), true);
    }

    /**
     * 创建秒杀订单DirectExchange交换机
     * @return
     */
    @Bean
    public DirectExchange seckillOrderExchange(){
        return new DirectExchange(env.getProperty("mq.pay.exchange.seckillOrder"), true,false);
    }


    /**
     * 创建秒杀订单队列绑定到交换机上
     * @return
     */
    @Bean
    public Binding seckillOrderExchangeBinding(){
        return BindingBuilder.bind(seckillOrderQueue()).to(orderExchange()).with(env.getProperty("mq.pay.routing.seckillOrderKey"));
    }
}
