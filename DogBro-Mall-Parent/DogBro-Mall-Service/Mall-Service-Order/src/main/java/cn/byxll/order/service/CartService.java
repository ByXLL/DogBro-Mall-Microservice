package cn.byxll.order.service;

import cn.byxll.order.pojo.OrderItem;
import entity.Result;

import java.util.List;

/**
 * 购物车service 接口类
 * @author By-Lin
 */
public interface CartService {
    /**
     * 加入购物车
     * @param number        个数
     * @param goodsId       商品id
     * @param userName      用户名称
     * @return              响应数据
     */
    Result<Boolean> add(Integer number, Long goodsId, String userName);

    /**
     * 根据用户名查询购物车列表
     * @param userName      用户名
     * @return              响应数据
     */
    Result<List<OrderItem>> findByUserName(String userName);
}
