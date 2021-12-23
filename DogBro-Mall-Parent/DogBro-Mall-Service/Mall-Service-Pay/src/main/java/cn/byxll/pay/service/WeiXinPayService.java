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
     * @param outTradeNo 客户端自定义订单编号
     * @param totalFee   交易金额,单位：分
     * @param orderType      订单类型 1->正常订单 2->秒杀订单
     * @return 响应数据
     */
    Result<Map<String,String>> createNative(String outTradeNo, String totalFee, Integer orderType);

    /**
     * 查询订单状态
     * @param outTradeNo    客户端自定义订单编号
     * @return              响应数据
     */
    Result<Map<String,String>> queryPayStatus(String outTradeNo);


}
