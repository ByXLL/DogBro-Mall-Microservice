package cn.byxll.order.service.impl;

import cn.byxll.goods.feign.SkuFeign;
import cn.byxll.goods.pojo.Sku;
import cn.byxll.order.dao.OrderItemMapper;
import cn.byxll.order.dao.OrderMapper;
import cn.byxll.order.dto.OrderDto;
import cn.byxll.order.pojo.Order;
import cn.byxll.order.pojo.OrderItem;
import cn.byxll.order.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import exception.OperationalException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import utils.IdWorker;
import utils.OAuthTokenDecode;

import java.util.*;

/**
 * Order业务层接口实现类
 * @author By-Lin
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final SkuFeign skuFeign;
    private final RedisTemplate redisTemplate;

    public OrderServiceImpl(OrderMapper orderMapper, OrderItemMapper orderItemMapper, SkuFeign skuFeign, RedisTemplate redisTemplate) {
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.skuFeign = skuFeign;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 新增Order
     * @param orderDto       Order dto实体
     * @return               响应数据
     */
    @Override
    @Transactional(rollbackFor = OperationalException.class)
    public Result<Boolean> add(OrderDto orderDto){
        IdWorker idWorker = new IdWorker();
        String userName = OAuthTokenDecode.getUserInfo().get("username");
        if(orderDto == null || org.apache.commons.lang.StringUtils.isEmpty(userName)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        Order order = new Order();
        BeanUtils.copyProperties(orderDto,order);
        order.setId(String.valueOf(idWorker.nextId()));
        order.setUsername(userName);
        order.setCreateTime(new Date());
        order.setUpdateTime(order.getCreateTime());
        order.setSourceType("1");
        order.setOrderStatus("0");
        order.setPayStatus("0");
        order.setIsDelete("0");
        // 校验金额


        // 获取购物车信息
        List<OrderItem> userCartList = redisTemplate.boundHashOps("Cart_" + userName).values();
        if(userCartList == null || orderDto.getSkuList().size() <1) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        List<OrderItem> orderItems = new ArrayList<>(16);
        List<Long> skuIdList = orderDto.getSkuList();
        for (OrderItem cartItem : userCartList) {
            Long id = Long.valueOf(cartItem.getSkuId());
            if(skuIdList.contains(id)) {
                orderItems.add(cartItem);
                // 清空购物车
                redisTemplate.boundHashOps("Cart_" + userName).delete(id);
            }
        }
        if(orderItems.size() < 1) { throw new OperationalException("创建订单失败"); }

        int totalNum = 0;
        int totalMoney = 0 ;
        // 封装Map<Long,Integer>  封装递减数据
        Map<Long,Integer> decrMap = new HashMap<>(16);
        for (OrderItem orderItem : orderItems) {
            totalNum += orderItem.getNum();
            totalMoney += orderItem.getMoney();
            orderItem.setId(String.valueOf(idWorker.nextId()));
            orderItem.setOrderId(order.getId());
            orderItem.setPayMoney(orderItem.getMoney());
            orderItem.setIsReturn("0");
            decrMap.put(Long.valueOf(orderItem.getSkuId()),orderItem.getNum());
        }
        order.setTotalNum(totalNum);
        order.setTotalMoney(totalMoney);
        order.setPayMoney(totalMoney);
        order.setBuyerRate("0");
        order.setConsignStatus("0");

        int i = orderMapper.insert(order);
        if(i<0) { throw new OperationalException("创建订单失败");}
        for (OrderItem orderItem : orderItems) {
            int row = orderItemMapper.insert(orderItem);
            if(row < 1) { throw new OperationalException("创建订单失败"); }
        }

        // 扣除库存
        Result<Boolean> result = skuFeign.decrCount(decrMap);
        if(!result.isFlag()){ throw new OperationalException("创建订单失败");  }
        return new Result<>(true, StatusCode.OK, "操作成功",order);
    }

    /**
     * 删除
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(String id){
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = orderMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改Order
     * @param order      Order实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(Order order){
        if(order == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = orderMapper.updateByPrimaryKey(order);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询Order
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Order> findById(String id) {
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        Order order = orderMapper.selectByPrimaryKey(id);
        if(order != null) { return new Result<>(true, StatusCode.OK, "查询成功", order); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有Order
     * @return      响应数据
     */
    @Override
    public Result<List<Order>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", orderMapper.selectAll());
    }

    /**
     * Order分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Order>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(orderMapper.selectAll()));
    }


    /**
     * Order条件分页查询
     * @param order      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<Order>> findPagerByParam(Order order, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(order);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<Order>(orderMapper.selectByExample(example)));
    }

    /**
     * Order多条件搜索方法
     * @param order      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<Order>> findList(Order order){
        if(order == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(order);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<Order>(orderMapper.selectByExample(example)));
    }


    /**
     * Order构建查询对象
     * @param order      Order实体
     * @return              查询对象
     */
    private Example createExample(Order order){
        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        if(order!=null){
            // 订单id
            if(!StringUtils.isEmpty(order.getId())){
                criteria.andEqualTo("id",order.getId());
            }
            // 数量合计
            if(!StringUtils.isEmpty(order.getTotalNum())){
                criteria.andEqualTo("totalNum",order.getTotalNum());
            }
            // 金额合计
            if(!StringUtils.isEmpty(order.getTotalMoney())){
                criteria.andEqualTo("totalMoney",order.getTotalMoney());
            }
            // 优惠金额
            if(!StringUtils.isEmpty(order.getPreMoney())){
                criteria.andEqualTo("preMoney",order.getPreMoney());
            }
            // 邮费
            if(!StringUtils.isEmpty(order.getPostFee())){
                criteria.andEqualTo("postFee",order.getPostFee());
            }
            // 实付金额
            if(!StringUtils.isEmpty(order.getPayMoney())){
                criteria.andEqualTo("payMoney",order.getPayMoney());
            }
            // 支付类型，1、在线支付、0 货到付款
            if(!StringUtils.isEmpty(order.getPayType())){
                criteria.andEqualTo("payType",order.getPayType());
            }
            // 订单创建时间
            if(!StringUtils.isEmpty(order.getCreateTime())){
                criteria.andEqualTo("createTime",order.getCreateTime());
            }
            // 订单更新时间
            if(!StringUtils.isEmpty(order.getUpdateTime())){
                criteria.andEqualTo("updateTime",order.getUpdateTime());
            }
            // 付款时间
            if(!StringUtils.isEmpty(order.getPayTime())){
                criteria.andEqualTo("payTime",order.getPayTime());
            }
            // 发货时间
            if(!StringUtils.isEmpty(order.getConsignTime())){
                criteria.andEqualTo("consignTime",order.getConsignTime());
            }
            // 交易完成时间
            if(!StringUtils.isEmpty(order.getEndTime())){
                criteria.andEqualTo("endTime",order.getEndTime());
            }
            // 交易关闭时间
            if(!StringUtils.isEmpty(order.getCloseTime())){
                criteria.andEqualTo("closeTime",order.getCloseTime());
            }
            // 物流名称
            if(!StringUtils.isEmpty(order.getShippingName())){
                criteria.andEqualTo("shippingName",order.getShippingName());
            }
            // 物流单号
            if(!StringUtils.isEmpty(order.getShippingCode())){
                criteria.andEqualTo("shippingCode",order.getShippingCode());
            }
            // 用户名称
            if(!StringUtils.isEmpty(order.getUsername())){
                criteria.andLike("username","%"+order.getUsername()+"%");
            }
            // 买家留言
            if(!StringUtils.isEmpty(order.getBuyerMessage())){
                criteria.andEqualTo("buyerMessage",order.getBuyerMessage());
            }
            // 是否评价
            if(!StringUtils.isEmpty(order.getBuyerRate())){
                criteria.andEqualTo("buyerRate",order.getBuyerRate());
            }
            // 收货人
            if(!StringUtils.isEmpty(order.getReceiverContact())){
                criteria.andEqualTo("receiverContact",order.getReceiverContact());
            }
            // 收货人手机
            if(!StringUtils.isEmpty(order.getReceiverMobile())){
                criteria.andEqualTo("receiverMobile",order.getReceiverMobile());
            }
            // 收货人地址
            if(!StringUtils.isEmpty(order.getReceiverAddress())){
                criteria.andEqualTo("receiverAddress",order.getReceiverAddress());
            }
            // 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面
            if(!StringUtils.isEmpty(order.getSourceType())){
                criteria.andEqualTo("sourceType",order.getSourceType());
            }
            // 交易流水号
            if(!StringUtils.isEmpty(order.getTransactionId())){
                criteria.andEqualTo("transactionId",order.getTransactionId());
            }
            // 订单状态 
            if(!StringUtils.isEmpty(order.getOrderStatus())){
                criteria.andEqualTo("orderStatus",order.getOrderStatus());
            }
            // 支付状态 0:未支付 1:已支付
            if(!StringUtils.isEmpty(order.getPayStatus())){
                criteria.andEqualTo("payStatus",order.getPayStatus());
            }
            // 发货状态 0:未发货 1:已发货 2:已送达
            if(!StringUtils.isEmpty(order.getConsignStatus())){
                criteria.andEqualTo("consignStatus",order.getConsignStatus());
            }
            // 是否删除
            if(!StringUtils.isEmpty(order.getIsDelete())){
                criteria.andEqualTo("isDelete",order.getIsDelete());
            }
        }
        return example;
    }

}
