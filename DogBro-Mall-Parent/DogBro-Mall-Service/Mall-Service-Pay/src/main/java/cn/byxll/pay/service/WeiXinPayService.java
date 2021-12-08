package cn.byxll.pay.service;

import entity.Result;

import java.util.Map;

/**
 * 微信支付 service 接口
 * @author By-Lin
 */
public interface WeiXinPayService {
    /**
     * 创建二维码
     * @param outTradeNo    客户端自定义订单编号
     * @param totalFee      交易金额,单位：分
     * @return              响应数据
     */
    Result<Boolean> createNative(String outTradeNo, String totalFee);
}
