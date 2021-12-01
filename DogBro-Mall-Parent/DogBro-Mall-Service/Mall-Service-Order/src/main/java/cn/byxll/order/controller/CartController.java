package cn.byxll.order.controller;

import cn.byxll.order.pojo.Order;
import cn.byxll.order.pojo.OrderItem;
import cn.byxll.order.service.impl.CartServiceImpl;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

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
    public Result<Boolean> add(@PathParam("number") Integer number, @PathParam("id") Long id){
        return cartService.add(number,id,"heima");
    }

    @GetMapping("/list")
    public Result<List<OrderItem>> getList(){
        return cartService.findByUserName("heima");
    }
}
