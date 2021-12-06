package cn.byxll.order.service.impl;

import cn.byxll.goods.feign.SkuFeign;
import cn.byxll.goods.feign.SpuFeign;
import cn.byxll.goods.pojo.Sku;
import cn.byxll.goods.pojo.Spu;
import cn.byxll.order.pojo.OrderItem;
import cn.byxll.order.service.CartService;
import entity.Result;
import entity.StatusCode;
import exception.OperationalException;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 购物车service 接口实现类
 * @author By-Lin
 */
@Service
public class CartServiceImpl implements CartService {
    private final RedisTemplate redisTemplate;
    private final SkuFeign skuFeign;
    private final SpuFeign spuFeign;

    public CartServiceImpl(RedisTemplate redisTemplate, SkuFeign skuFeign, SpuFeign spuFeign) {
        this.redisTemplate = redisTemplate;
        this.skuFeign = skuFeign;
        this.spuFeign = spuFeign;
    }

    /**
     * 加入购物车
     *
     * @param number  个数
     * @param goodsId 商品id
     * @param userName 用户名字
     * @return 响应数据
     */
    @Override
    @Transactional(rollbackFor = OperationalException.class)
    public Result<Boolean> add(Integer number, Long goodsId, String userName) {
        if(number == null || goodsId == null || StringUtils.isEmpty(userName)) {
            return new Result<>(false,StatusCode.ARGERROR,"参数异常");
        }
        // 当购物车数量<=0时候，需要移除该商品信息
        if(number <=0) {
            redisTemplate.boundHashOps("Cart_"+userName).delete(goodsId);
            // 如果购物车数量为空，则将购物车也删除
            Long size = redisTemplate.boundHashOps("Cart_" + userName).size();
            if(size == null || size<0) {
                Boolean delete = redisTemplate.delete("Cart_" + userName);
            }
            return new Result<>(true,StatusCode.OK,"加入购物车成功");
        }
        // 查询商品的详情
        // 1、查询sku
        Result<Sku> skuResult = skuFeign.findById(goodsId);
        Sku sku = skuResult.getData();
        if(sku == null) { throw new OperationalException("加入购物车失败"); }
        // 2、查询spu
        Result<Spu> spuResult = spuFeign.findById(sku.getSpuId());
        Spu spu = spuResult.getData();
        if(spu == null) { throw new OperationalException("加入购物车失败");  }
        OrderItem orderItem = createOrderItem(number, sku, spu);
        // 将购物车数据存入Redis
        redisTemplate.boundHashOps("Cart_" + userName).put(goodsId,orderItem);
        return new Result<>(true, StatusCode.OK, "加入购物车成功");
    }

    /**
     * 根据用户名查询购物车列表
     * @param userName 用户名
     * @return 响应数据
     */
    @Override
    public Result<List<OrderItem>> findByUserName(String userName) {
        List list = redisTemplate.boundHashOps("Cart_" + userName).values();
        return new Result<>(false,StatusCode.OK,"获取购物车列表成功",list);
    }

    /**
     * 构建 orderItem
     * @param number    个数
     * @param sku       sku实体
     * @param spu       spu实体
     * @return  OrderItem
     */
    private OrderItem createOrderItem(Integer number, Sku sku, Spu spu) {
        // 将加入购物车的商品信息封装成OrderItem
        OrderItem orderItem = new OrderItem();
        orderItem.setCategoryId1(spu.getCategory1Id());
        orderItem.setCategoryId2(spu.getCategory2Id());
        orderItem.setCategoryId3(spu.getCategory3Id());
        orderItem.setSpuId(String.valueOf(spu.getId()));
        orderItem.setSkuId(sku.getId().toString());
        orderItem.setName(sku.getName());
        orderItem.setPrice(sku.getPrice());
        orderItem.setNum(number);
        orderItem.setMoney(number *sku.getPrice());
        orderItem.setWeight(sku.getWeight());
        orderItem.setImage(sku.getImage());
        return orderItem;
    }
}
