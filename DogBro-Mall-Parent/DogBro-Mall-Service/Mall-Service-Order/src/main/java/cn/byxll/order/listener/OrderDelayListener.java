package cn.byxll.order.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单超时MQ监听器
 * @author By-Lin
 */
@Component
@RabbitListener(queues = "orderListenerQueue")
public class OrderDelayListener {
    /**
     * 监听订单超时消息 处理器
     * @param message       消息
     */
    @RabbitHandler
    public void getOrderDelayMessage(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("监听到订单超时消息："+message);
        simpleDateFormat.format("监听到订单超时消息时间：" + new Date());
    }
}
