package cn.byxll.order.feign;

import cn.byxll.order.pojo.OrderItem;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 订单 商品 feign
 * @author By-Lin
 */
@FeignClient(value = "order")
@RequestMapping("/orderItem")
public interface OrderItemFeign {

    @PostMapping("/search")
    Result<List<OrderItem>> findList(@RequestBody OrderItem orderItem);
}
