package cn.byxll.seckill.service.impl;

import cn.byxll.seckill.dao.SeckillGoodsMapper;
import cn.byxll.seckill.dao.SeckillOrderMapper;
import cn.byxll.seckill.pojo.SeckillGoods;
import cn.byxll.seckill.pojo.SeckillOrder;
import cn.byxll.seckill.service.SeckillOrderService;
import cn.byxll.seckill.task.MultiThreadingCreateOrder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.SeckillStatus;
import entity.StatusCode;
import exception.OperationalException;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * SeckillOrder业务层接口实现类
 * @author By-Lin
 */
@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

    private final RedisTemplate redisTemplate;
    private final SeckillGoodsMapper seckillGoodsMapper;
    private final SeckillOrderMapper seckillOrderMapper;
    private final MultiThreadingCreateOrder multiThreadingCreateOrder;

    public SeckillOrderServiceImpl(RedisTemplate redisTemplate, SeckillGoodsMapper seckillGoodsMapper, SeckillOrderMapper seckillOrderMapper, MultiThreadingCreateOrder multiThreadingCreateOrder) {
        this.redisTemplate = redisTemplate;
        this.seckillGoodsMapper = seckillGoodsMapper;
        this.seckillOrderMapper = seckillOrderMapper;
        this.multiThreadingCreateOrder = multiThreadingCreateOrder;
    }

    /**
     * 添加秒杀订单
     *
     * @param time 当前时间
     * @param id   秒杀商品id
     * @param userName 用户名
     * @return 响应数据
     */
    @Override
    public Result<Boolean> add(String time, Long id, String userName) {
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(time) || id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        // 记录用户排队次数 用于限制一个用户只能抢购一个  increment(key,递增的值)
        Long userQueueCount = redisTemplate.boundHashOps("UserQueueCount").increment(userName, 1);
        if(userQueueCount >1) { return new Result<>(false,StatusCode.ERROR,"抢购失败，禁止重复抢单！",null); }
        // 创建排队对象
        SeckillStatus seckillStatus = new SeckillStatus(userName, new Date(), 1, id, time);
        // 将用户抢单信息存入redis队列  有个问题 需要设置超时时间
        redisTemplate.boundListOps("SeckillOrderQueue").leftPush(seckillStatus);
        // 用户抢单状态存入Redis -> 用于查询
        redisTemplate.boundHashOps("UserQueueStatus").put(userName,seckillStatus);
        // 多线程执行抢单
        multiThreadingCreateOrder.createOrder();
        //        IdWorker idWorker = new IdWorker();
//        String userName = "zhangsan";
//        String nameSpace = "SeckillGoods_" + time;
//        SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps(nameSpace).get(id);
//        if (seckillGoods == null || seckillGoods.getStockCount() <= 0) {
//            return new Result<>(false,StatusCode.ERROR,"商品已经抢完了",false);
//        }
//        SeckillOrder seckillOrder = new SeckillOrder();
//        seckillOrder.setId(idWorker.nextId());
//        seckillOrder.setSeckillId(id);
//        seckillOrder.setMoney(seckillGoods.getCostPrice());
//        seckillOrder.setUserId(userName);
//        seckillOrder.setCreateTime(new Date());
//        seckillOrder.setStatus("0");
////        int i = seckillOrderMapper.insert(seckillOrder);
//
//        /**
//         * 将订单信息存储起来
//         * 一个用户只能有一个未支付秒杀订单
//         * 订单存入redis
//         * Hash
//         *      nameSpace -> SeckillOrder
//         *                      userName: SeckillOrder
//         */
//        redisTemplate.boundHashOps("SeckillOrder").put(userName,seckillOrder);
//
//        /**
//         * 库存递减 redis中保存的商品信息中的商品个数递减
//         * 如果递减了是最后一个，则直接将redis中的商品信息进行删除
//         * 同步redis中商品信息到mysql中
//         */
//        seckillGoods.setStockCount(seckillGoods.getStockCount()-1);
//        if(seckillGoods.getStockCount() <= 0) {
//            // 同步到mysql
//            int i1 = seckillGoodsMapper.updateByPrimaryKeySelective(seckillGoods);
//            // 移除Redis中的商品信息
//            redisTemplate.boundHashOps(nameSpace).delete(id);
//        }else {
//            // 同步到redis
//            redisTemplate.boundHashOps(nameSpace).put(id,seckillGoods);
//        }
        return new Result<>(true, StatusCode.OK, "正在排队中...");
//        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
//        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(Long id){
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = seckillOrderMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 通过用户名删除订单
     * @param userName 用户名
     * @return 响应数据
     */
    @Override
    public Result<Boolean> deleteOrderByUserName(String userName) {
        if(StringUtils.isEmpty(userName)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        // 查询用户排队信息
        SeckillStatus seckillStatus = (SeckillStatus) redisTemplate.boundHashOps("UserQueueStatus").get(userName);
        // 删除redis中的订单
        redisTemplate.boundHashOps("SeckillOrder").delete(userName);
        // 删除用户排队信息
        clearUserQueue(userName);
        // region 回滚库存
        String nameSpace = "SeckillGoods_" + seckillStatus.getTime();
        SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps(nameSpace).get(seckillStatus.getGoodsId());

        // 如果商品为空
        if(seckillGoods == null) {
            // 从数据库中查询
            seckillGoods = seckillGoodsMapper.selectByPrimaryKey(seckillStatus.getGoodsId());
            if(seckillGoods == null) { throw new OperationalException("删除订单失败");}
            // 更新数据库中的数据
            seckillGoods.setStockCount(seckillGoods.getStockCount()+1);
            seckillGoodsMapper.updateByPrimaryKeySelective(seckillGoods);
        }else {
            seckillGoods.setStockCount(seckillGoods.getStockCount()+1);
        }
        // 同步到redis缓存
        redisTemplate.boundHashOps(nameSpace).put(seckillGoods.getId(),seckillGoods);
        // endregion

        // 商品队列添加一个
        redisTemplate.boundListOps("SeckillGoodsCountList_"+seckillGoods.getId()).leftPush(seckillGoods.getId());
        return null;
    }

    /**
     * 修改SeckillOrder
     * @param seckillOrder      SeckillOrder实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(SeckillOrder seckillOrder){
        if(seckillOrder == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = seckillOrderMapper.updateByPrimaryKey(seckillOrder);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改秒杀订单 支付状态
     *
     * @param userName      用户名
     * @param transactionId 交易流水号
     * @param payTime       支付时间
     * @return 响应数据
     */
    @Override
    public Result<Boolean> updatePayStatus(String userName, String transactionId, String payTime) {
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(transactionId) || StringUtils.isEmpty(payTime)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        try {
            SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.boundHashOps("SeckillOrder").get(userName);
            if(seckillOrder == null) { return new Result<>(false,StatusCode.ERROR,"修改订单失败"); }
            // 修改订单状态
            seckillOrder.setTransactionId(transactionId);
            seckillOrder.setStatus("1");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            seckillOrder.setPayTime(simpleDateFormat.parse(payTime));
            int i = seckillOrderMapper.insertSelective(seckillOrder);
            // 删除redis中的订单
            redisTemplate.boundHashOps("SeckillOrder").delete(userName);
            // 删除用户的抢单信息
            clearUserQueue(userName);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        // 清理用户排队信息
        return null;
    }

    /**
     * 根据ID查询SeckillOrder
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<SeckillOrder> findById(Long id) {
        if(id == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        SeckillOrder seckillOrder = seckillOrderMapper.selectByPrimaryKey(id);
        if(seckillOrder != null) { return new Result<>(true, StatusCode.OK, "查询成功", seckillOrder); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有SeckillOrder
     * @return      响应数据
     */
    @Override
    public Result<List<SeckillOrder>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", seckillOrderMapper.selectAll());
    }

    /**
     * SeckillOrder分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<SeckillOrder>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(seckillOrderMapper.selectAll()));
    }


    /**
     * SeckillOrder条件分页查询
     * @param seckillOrder      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<SeckillOrder>> findPagerByParam(SeckillOrder seckillOrder, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(seckillOrder);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<SeckillOrder>(seckillOrderMapper.selectByExample(example)));
    }

    /**
     * SeckillOrder多条件搜索方法
     * @param seckillOrder      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<SeckillOrder>> findList(SeckillOrder seckillOrder){
        if(seckillOrder == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(seckillOrder);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<SeckillOrder>(seckillOrderMapper.selectByExample(example)));
    }

    /**
     * 订单状态查询
     * @return 响应数据
     */
    @Override
    public Result<SeckillOrder> queryStatus() {
        String userName = "zhangsan";
        if (StringUtils.isEmpty(userName)) {  return new Result<>(false, StatusCode.ARGERROR, "参数异常",null);  }
        // 根据用户名去Redis 用户抢单状态队列中查询
        SeckillStatus seckillStatus = (SeckillStatus) redisTemplate.boundHashOps("UserQueueStatus").get(userName);
        if(seckillStatus != null) { return new Result<>(true, StatusCode.OK, "状态查询成功",seckillStatus); }
        return new Result<>(false, StatusCode.NOTFOUNDERROR, "抢单失败",null);
    }

    /**
     * SeckillOrder构建查询对象
     * @param seckillOrder      SeckillOrder实体
     * @return              查询对象
     */
    private Example createExample(SeckillOrder seckillOrder){
        Example example = new Example(SeckillOrder.class);
        Example.Criteria criteria = example.createCriteria();
        if(seckillOrder!=null){
            // 主键
            if(seckillOrder.getId() != null){
                criteria.andEqualTo("id",seckillOrder.getId());
            }
            // 秒杀商品ID
            if(seckillOrder.getSeckillId() != null){
                criteria.andEqualTo("seckillId",seckillOrder.getSeckillId());
            }
            // 支付金额
            if(!StringUtils.isEmpty(seckillOrder.getMoney())){
                criteria.andEqualTo("money",seckillOrder.getMoney());
            }
            // 用户
            if(!StringUtils.isEmpty(seckillOrder.getUserId())){
                criteria.andEqualTo("userId",seckillOrder.getUserId());
            }
            // 商家
            if(!StringUtils.isEmpty(seckillOrder.getSellerId())){
                criteria.andEqualTo("sellerId",seckillOrder.getSellerId());
            }
            // 创建时间
            if(!StringUtils.isEmpty(String.valueOf(seckillOrder.getCreateTime()))){
                criteria.andEqualTo("createTime",seckillOrder.getCreateTime());
            }
            // 支付时间
            if(!StringUtils.isEmpty(String.valueOf(seckillOrder.getPayTime()))){
                criteria.andEqualTo("payTime",seckillOrder.getPayTime());
            }
            // 状态，0未支付，1已支付
            if(!StringUtils.isEmpty(seckillOrder.getStatus())){
                criteria.andEqualTo("status",seckillOrder.getStatus());
            }
            // 收货人地址
            if(!StringUtils.isEmpty(seckillOrder.getReceiverAddress())){
                criteria.andEqualTo("receiverAddress",seckillOrder.getReceiverAddress());
            }
            // 收货人电话
            if(!StringUtils.isEmpty(seckillOrder.getReceiverMobile())){
                criteria.andEqualTo("receiverMobile",seckillOrder.getReceiverMobile());
            }
            // 收货人
            if(!StringUtils.isEmpty(seckillOrder.getReceiver())){
                criteria.andEqualTo("receiver",seckillOrder.getReceiver());
            }
            // 交易流水
            if(!StringUtils.isEmpty(seckillOrder.getTransactionId())){
                criteria.andEqualTo("transactionId",seckillOrder.getTransactionId());
            }
        }
        return example;
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
