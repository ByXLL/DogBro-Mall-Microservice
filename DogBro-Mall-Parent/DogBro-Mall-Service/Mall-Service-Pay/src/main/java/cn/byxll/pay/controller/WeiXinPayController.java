package cn.byxll.pay.controller;

import cn.byxll.pay.service.impl.WeiXinPayServiceImpl;
import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayUtil;
import entity.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付 控制器
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/wxPay")
public class WeiXinPayController {
    private final WeiXinPayServiceImpl weiXinPayService;
    private final RabbitTemplate rabbitTemplate;

    public WeiXinPayController(WeiXinPayServiceImpl weiXinPayService, RabbitTemplate rabbitTemplate) {
        this.weiXinPayService = weiXinPayService;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 创建二维码
     * @param outTradeNo     订单编号
     * @param money          订单金额
     * @param orderType      订单类型 1->正常订单 2->秒杀订单
     * @param userName       用户名
     * @return               响应数据
     */
    @RequestMapping("/createQRCode")
    public Result<Map<String,String>> createQRCode(String outTradeNo, String money, Integer orderType, String userName) {
        return weiXinPayService.createNative(outTradeNo,money,orderType, userName);
    }

    /**
     * 查询订单支付状态
     * @param outTradeNo        订单编号
     * @return                  响应数据
     */
    @GetMapping(value = "/queryStatus")
    public Result<Map<String,String>> queryStatus(String outTradeNo){
        return weiXinPayService.queryPayStatus(outTradeNo);
    }

    /**
     * 支付回调 响应数据回传给腾讯
     * @param request       请求实体
     * @return              响应数据
     */
    @RequestMapping(value = "/notify/url")
    public String notifyUrl(HttpServletRequest request){
        // 输入流获取腾讯回传的数据
        InputStream inStream;
        try {
            //读取支付回调数据
            inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            // 将支付回调数据转换成xml字符串
            String result = outSteam.toString("utf-8");
            System.out.println("微信回调数据: "+result);
            //将xml字符串转换成Map结构
            Map<String, String> map = WXPayUtil.xmlToMap(result);

            // 获取自定义的参数中的 MQ 的参数
            String attachStr = map.get("attach");
            Map<String,String> attachMap = JSON.parseObject(attachStr,Map.class);
            String exchange = attachMap.get("exchange");
            String routingKey = attachMap.get("routingKey");

            // 发送MQ
            rabbitTemplate.convertAndSend(exchange,routingKey, JSON.toJSONString(map));

            //响应数据设置
            Map<String,String> respMap = new HashMap<String,String >(16);
            respMap.put("return_code","SUCCESS");
            respMap.put("return_msg","OK");
            return WXPayUtil.mapToXml(respMap);
        } catch (Exception e) {
            e.printStackTrace();
            //记录错误日志
        }
        return null;
    }
}
