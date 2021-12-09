package cn.byxll.order.listener;

import cn.byxll.order.service.impl.OrderServiceImpl;
import com.alibaba.fastjson.JSON;
import exception.OperationalException;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Map;

/**
 * MQ 监听器
 * @RabbitListener 声明监听的队列
 * @author By-Lin
 */
@Component
@RabbitListener(queues = "${mq.pay.queue.order}")
public class OrderMessageListener {
    private final OrderServiceImpl orderService;

    public OrderMessageListener(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    /**
     * MQ处理器 用户监听支付结果
     * @param message      数据
     */
    @RabbitHandler
    public void getMessage(String message) throws ParseException {
        if(StringUtils.isEmpty(message)) { throw new OperationalException("MQ 处理订单回调失败->参数异常");}
        System.out.println("MQ监听到的支付结果： "+message);
        //将数据转成Map
        Map<String,String> result = JSON.parseObject(message,Map.class);

        // 通信标识
        String returnCode = result.get("return_code");
        // 业务结果
        String resultCode = result.get("result_code");

        //业务结果 result_code=SUCCESS/FAIL，修改订单状态
        if(returnCode.equalsIgnoreCase("success") ){
            //获取订单号
            String outTradeNo = result.get("out_trade_no");
            //业务结果
            if(resultCode.equalsIgnoreCase("success")){
                if(outTradeNo!=null){
                    //修改订单状态  out_trade_no
                    orderService.updateOrderPayStatus(outTradeNo,result.get("time_end"),result.get("transaction_id"));
                }
            }else{
                // 支付失败，取消订单，库存回滚
                orderService.cancelOrder(outTradeNo);

                // 向微信关闭支付








            }
        }

    }
}
