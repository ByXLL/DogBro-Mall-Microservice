package cn.byxll.seckill.task;

import cn.byxll.seckill.dao.SeckillGoodsMapper;
import cn.byxll.seckill.dao.SeckillOrderMapper;
import cn.byxll.seckill.pojo.SeckillGoods;
import cn.byxll.seckill.pojo.SeckillOrder;
import entity.Result;
import entity.StatusCode;
import exception.OperationalException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import utils.IdWorker;

import java.util.Date;

/**
 * 异步执行
 * @author By-Lin
 */
@Component
public class MultiThreadingCreateOrder {
    private final RedisTemplate redisTemplate;
    private final SeckillGoodsMapper seckillGoodsMapper;
    private final SeckillOrderMapper seckillOrderMapper;

    public MultiThreadingCreateOrder(RedisTemplate redisTemplate, SeckillGoodsMapper seckillGoodsMapper, SeckillOrderMapper seckillOrderMapper) {
        this.redisTemplate = redisTemplate;
        this.seckillGoodsMapper = seckillGoodsMapper;
        this.seckillOrderMapper = seckillOrderMapper;
    }

    /**
     * 多线程去执行
     * @Async 该方法会通过异步的方式执行
     */
    @Async
    public void createOrder() {
        String time = "2021121616";
        Long id = 6l;
        IdWorker idWorker = new IdWorker();
        String userName = "zhangsan";
        String nameSpace = "SeckillGoods_" + time;
        SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps(nameSpace).get(id);
        if (seckillGoods == null || seckillGoods.getStockCount() <= 0) {
            throw new OperationalException("商品已经抢完了");
        }
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setId(idWorker.nextId());
        seckillOrder.setSeckillId(id);
        seckillOrder.setMoney(seckillGoods.getCostPrice());
        seckillOrder.setUserId(userName);
        seckillOrder.setCreateTime(new Date());
        seckillOrder.setStatus("0");
//        int i = seckillOrderMapper.insert(seckillOrder);

        /**
         * 将订单信息存储起来
         * 一个用户只能有一个未支付秒杀订单
         * 订单存入redis
         * Hash
         *      nameSpace -> SeckillOrder
         *                      userName: SeckillOrder
         */
        redisTemplate.boundHashOps("SeckillOrder").put(userName,seckillOrder);

        /**
         * 库存递减 redis中保存的商品信息中的商品个数递减
         * 如果递减了是最后一个，则直接将redis中的商品信息进行删除
         * 同步redis中商品信息到mysql中
         */
        seckillGoods.setStockCount(seckillGoods.getStockCount()-1);
        if(seckillGoods.getStockCount() <= 0) {
            // 同步到mysql
            int i1 = seckillGoodsMapper.updateByPrimaryKeySelective(seckillGoods);
            // 移除Redis中的商品信息
            redisTemplate.boundHashOps(nameSpace).delete(id);
        }else {
            // 同步到redis
            redisTemplate.boundHashOps(nameSpace).put(id,seckillGoods);
        }
    }
}
