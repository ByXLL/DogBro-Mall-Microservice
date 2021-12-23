package cn.byxll.seckill.timer;

import cn.byxll.seckill.dao.SeckillGoodsMapper;
import cn.byxll.seckill.pojo.SeckillGoods;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import utils.DateUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 定时任务
 * 定时将秒杀商品存入到Redis缓存
 * @author By-Lin
 */
@Component
public class SeckillGoodsPushTask {
    private final SeckillGoodsMapper seckillGoodsMapper;
    private final RedisTemplate redisTemplate;

    public SeckillGoodsPushTask(SeckillGoodsMapper seckillGoodsMapper, RedisTemplate redisTemplate) {
        this.seckillGoodsMapper = seckillGoodsMapper;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 每30秒执行一次
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void loadGoodsPushRedis(){
        System.out.println("--------------------- 开始同步秒杀商品 -----------------------");
        // 1、查询符合当前参与秒杀的时间菜单
        List<Date> dateMenus = DateUtil.getDateMenus();

        // 2、循环查询每个时间区间的秒杀商品
        for (Date menuDate : dateMenus) {
            // 时间的字符串格式 yyyyHHddHH 用于redis 的命名空间
            String timeSpace = "SeckillGoods_" + DateUtil.data2str(menuDate,"yyyyMMddHH");

            Example example = new Example(SeckillGoods.class);
            Example.Criteria criteria = example.createCriteria();

            // 审核状态
            criteria.andEqualTo("status","1");
            // 秒杀商品库存 > 0
            criteria.andGreaterThan("stockCount",0);
            // 时间菜单的开始时间 =< 开始时间
            criteria.andGreaterThanOrEqualTo("startTime",menuDate);
            // 结束时间 < 时间菜单的开始时间 + 2小时
            criteria.andLessThan("endTime",DateUtil.addDateHour(menuDate,2));

            // 排除已经存入到了Redis中的商品
            Set keys = redisTemplate.boundHashOps(timeSpace).keys();
            if(keys!=null && keys.size() >0) {
                // 不包含
                criteria.andNotIn("id",keys);
            }

            // 3、查询数据库数据
            List<SeckillGoods> seckillGoodsList = seckillGoodsMapper.selectByExample(example);
            System.out.println(seckillGoodsList);
            for (SeckillGoods seckillGoods : seckillGoodsList) {
                // 4、 存入redis
                redisTemplate.boundHashOps(timeSpace).put(seckillGoods.getId(),seckillGoods);
                System.out.println("商品ID："+seckillGoods.getId()+"----存入到了Redis---"+timeSpace);
                // 给每一个商品做个队列 队列中存放 n个数 n=商品的个数 用于在下单的时候做库存校验，解决并发下库存超卖问题
                Long[] allIdS = putAllIdS(seckillGoods.getId(), seckillGoods.getStockCount());
                redisTemplate.boundListOps("SeckillGoodsCountList_"+seckillGoods.getId()).leftPush(allIdS);
            }
        }
    }

    /**
     *  获取每个秒杀商品的的ID集合
     * @param goodsId       秒杀商品id
     * @param num           商品个数
     * @return              个数数组
     */
    public Long[] putAllIdS(Long goodsId, Integer num) {
        Long[] ids = new Long[num];
        Arrays.fill(ids, goodsId);
        return ids;
    }
}
