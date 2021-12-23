package cn.byxll.seckill.task;

import cn.byxll.seckill.dao.SeckillGoodsMapper;
import cn.byxll.seckill.dao.SeckillOrderMapper;
import cn.byxll.seckill.pojo.SeckillGoods;
import cn.byxll.seckill.pojo.SeckillOrder;
import entity.Result;
import entity.SeckillStatus;
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
        // 从redis队列中获取用户排队信息
        SeckillStatus seckillStatus = (SeckillStatus) redisTemplate.boundListOps("SeckillOrderQueue").rightPop();
        if (seckillStatus == null) {return;}
        String time = seckillStatus.getTime();
        Long id = seckillStatus.getGoodsId();
        IdWorker idWorker = new IdWorker();
        String userName = seckillStatus.getUserName();
        String nameSpace = "SeckillGoods_" + time;
        // 判断redis 当前商品个数队列中保存的商品个数
        Object sGoods = redisTemplate.boundListOps("SeckillGoodsCountList_" + seckillStatus.getGoodsId()).rightPop();
        // 如果获取不到，则代表秒杀商品不存在
        if(sGoods == null) {
            // 没有库存 清理当前用户的排队信息
            clearUserQueue(userName);
            throw new OperationalException("商品已经抢完了");
        }
        // 如果能获取到，则正常下单
        try {
            // 为了测试多线程 开启睡眠
//            System.out.println("睡眠一会再下单");
//            Thread.sleep(10000);

            // 判断库存 多线程会有问题 使用redis 给每一个商品添加一个队列，将商品id和商品个数存进去，在方法最开始的地方去判断库存
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

            /*
             * 将订单信息存储起来
             * 一个用户只能有一个未支付秒杀订单
             * 订单存入redis
             * Hash
             *      nameSpace -> SeckillOrder
             *                      userName: SeckillOrder
             */
            redisTemplate.boundHashOps("SeckillOrder").put(userName, seckillOrder);

            /*
             * 库存递减 redis中保存的商品信息中的商品个数递减
             * 如果递减了是最后一个，则直接将redis中的商品信息进行删除
             * 同步redis中商品信息到mysql中
             */
            seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);

            // 获取该商品在redis队列中的个数
            Long size = redisTemplate.boundListOps("SeckillGoodsCountList_" + seckillStatus.getGoodsId()).size();

            // 处理商品数据同步不精确问题
//            if (seckillGoods.getStockCount() <= 0) {
            if (size <= 0) {
                seckillGoods.setStockCount(size.intValue());
                // 同步到mysql
                int i1 = seckillGoodsMapper.updateByPrimaryKeySelective(seckillGoods);
                if(i1 < 1 ) { throw new OperationalException("抢单库存同步失败"); }
                // 移除Redis中的商品信息
                redisTemplate.boundHashOps(nameSpace).delete(id);
            } else {
                // 同步到redis
                redisTemplate.boundHashOps(nameSpace).put(id, seckillGoods);
            }
            // 更新下单状态
            seckillStatus.setOrderId(seckillOrder.getId());
            seckillStatus.setMoney(Float.valueOf(seckillGoods.getCostPrice()));
            seckillStatus.setStatus(2);
            // 更新用户抢单队列信息
            redisTemplate.boundHashOps("UserQueueStatus").put(userName,seckillStatus);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清理用户的排队和抢单信息
     * @param userName      用户名
     */
    private void clearUserQueue(String userName) {
        // 清空当前用户的排队次数
        redisTemplate.boundHashOps("UserQueueCount").delete(userName);
        // 清除用户的排队抢单信息
        redisTemplate.boundHashOps("UserQueueStatus").delete(userName);
    }
}
