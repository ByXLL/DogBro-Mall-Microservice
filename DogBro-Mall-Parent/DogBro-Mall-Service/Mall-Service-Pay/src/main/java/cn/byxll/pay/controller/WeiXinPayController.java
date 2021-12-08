package cn.byxll.pay.controller;

import cn.byxll.pay.service.impl.WeiXinPayServiceImpl;
import entity.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信支付 控制器
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/wxPay")
public class WeiXinPayController {
    private final WeiXinPayServiceImpl weiXinPayService;

    public WeiXinPayController(WeiXinPayServiceImpl weiXinPayService) {
        this.weiXinPayService = weiXinPayService;
    }

    @GetMapping("/createQRCode")
    public Result<Boolean> createQRCode(String outtradeno, String money) {
        return weiXinPayService.createNative(outtradeno,money);
    }
}
