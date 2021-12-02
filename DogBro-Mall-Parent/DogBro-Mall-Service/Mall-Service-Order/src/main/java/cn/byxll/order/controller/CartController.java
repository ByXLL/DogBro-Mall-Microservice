package cn.byxll.order.controller;

import cn.byxll.order.pojo.Order;
import cn.byxll.order.pojo.OrderItem;
import cn.byxll.order.service.impl.CartServiceImpl;
import entity.Result;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;
import utils.JwtUtil;
import utils.OAuthTokenDecode;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * 购物车 控制器类
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public Result<Boolean> add(@PathParam("number") Integer number, @PathParam("id") Long id) throws Exception {
//        // 通过携带过来的token获取当登录的用户信息
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
//        String tokenValue = details.getTokenValue();
//        Claims claims = JwtUtil.parseJWT(tokenValue);

        return cartService.add(number,id,getUserName());
    }

    @GetMapping("/list")
    public Result<List<OrderItem>> getList(){
        return cartService.findByUserName(getUserName());
    }

    /**
     * 通过获取 token 并解析出用户名
     * @return
     */
    private String getUserName() {
        // 通过携带过来的token获取当登录的用户信息
        Map<String, String> userInfo = OAuthTokenDecode.getUserInfo();
        return userInfo.get("username");
    }
}
