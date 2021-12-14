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
public interface OrderItemFeign {

    /**
     * 多条件搜索品牌数据
     * @param orderItem     OrderItem实体
     * @return              响应数据
     */
    @PostMapping("/orderItem/search")
    Result<List<OrderItem>> findList(@RequestBody OrderItem orderItem);
}
