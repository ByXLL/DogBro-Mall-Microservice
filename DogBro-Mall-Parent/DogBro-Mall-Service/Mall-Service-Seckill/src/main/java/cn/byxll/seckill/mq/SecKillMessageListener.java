package cn.byxll.seckill.mq;

import cn.byxll.seckill.service.impl.SeckillOrderServiceImpl;
import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 秒杀商品支付 MQ 监听器
 * @author By-Lin
 */
@Component
@RabbitListener(queues = "${mq.pay.queue.seckillOrder}")
public class SecKillMessageListener {
    private final SeckillOrderServiceImpl seckillOrderService;

    public SecKillMessageListener(SeckillOrderServiceImpl seckillOrderService) {
        this.seckillOrderService = seckillOrderService;
    }

    /**
     * 消息处理器
     * @param message       消息
     */
    @RabbitHandler
    public void getMessage(String message) {
        System.out.println("监听到->秒杀订单支付成功");
        try {
            Map<String,String> resultMap = JSON.parseObject(message,Map.class);
            // 通信标识
            String returnCode = resultMap.get("return_code");
            String outTradeNo = resultMap.get("out_trade_no");
            String transactionId = resultMap.get("transaction_id");
            String payTime = resultMap.get("time_end");
            String attach = resultMap.get("attach");
            Map<String,String> attachMap = WXPayUtil.xmlToMap(attach);
            if("SUCCESS".equals(returnCode)) {
                // 业务标识
                String resultCode = resultMap.get("result_code");
                String userName = attachMap.get("attachMap");
                if("SUCCESS".equals(resultCode)) {
                    // 修改订单状态 清理用户排队信息
                    seckillOrderService.updatePayStatus(userName, transactionId, payTime);
                }else {
                    // 删除订单（真实工作中将订单写入mysql） -> 回滚库存
                    seckillOrderService.deleteOrderByUserName(userName);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将支付信息转成map
    }
}
