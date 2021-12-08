package cn.byxll.pay.service.impl;

import cn.byxll.pay.service.WeiXinPayService;
import com.github.wxpay.sdk.WXPayUtil;
import entity.Result;
import entity.StatusCode;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.HttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付 service 接口实现类
 * @author By-Lin
 */
@Service
public class WeiXinPayServiceImpl implements WeiXinPayService {
    @Value("${weixin.appid}")
    private String appid;

    @Value("${weixin.partner}")
    private String partner;

    @Value("${weixin.partnerkey}")
    private String partnerkey;

    @Value("${weixin.notifyurl}")
    private String notifyurl;

    /**
     * 创建二维码
     * @param outTradeNo 客户端自定义订单编号
     * @param totalFee   交易金额,单位：分
     * @return 响应数据
     */
    @Override
    public Result<Boolean> createNative(String outTradeNo, String totalFee) {
        try {
            //1、封装参数
            HashMap<String,String> param = new HashMap<String,String>(16);
            // 应用ID
            param.put("appid", appid);
            // 商户ID号
            param.put("mch_id", partner);
            // 随机数
            param.put("nonce_str", WXPayUtil.generateNonceStr());
            // 订单描述
            param.put("body", "畅购");
            // 商户订单号
            param.put("out_trade_no",outTradeNo);
            // 交易金额
            param.put("total_fee", totalFee);
            // 终端IP
            param.put("spbill_create_ip", "127.0.0.1");
            // 回调地址
            param.put("notify_url", notifyurl);
            // 交易类型
            param.put("trade_type", "NATIVE");

            //2、将参数转成xml字符，并携带签名
            String paramXml = WXPayUtil.generateSignedXml(param, partnerkey);

            ///3、执行请求
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            httpClient.setHttps(true);
            httpClient.setXmlParam(paramXml);
            httpClient.post();

            //4、获取参数
            String content = httpClient.getContent();
            Map<String, String> stringMap = WXPayUtil.xmlToMap(content);
            String resultCode = stringMap.get("result_code");
            String errCodeDes = stringMap.get("err_code_des");
            System.out.println("stringMap:"+stringMap);
            if("FAIL".equals(resultCode)) { return new Result<>(false, StatusCode.ERROR, errCodeDes); }
            //5、获取部分页面所需参数
            Map<String,String> dataMap = new HashMap<String,String>(16);
            dataMap.put("code_url",stringMap.get("code_url"));
            dataMap.put("out_trade_no",totalFee);
            dataMap.put("total_fee",totalFee);
            return new Result<>(true, StatusCode.OK, "创建订单支付二维码成功",dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(false, StatusCode.ERROR, "创建订单支付二维码失败");
        }
    }
}
