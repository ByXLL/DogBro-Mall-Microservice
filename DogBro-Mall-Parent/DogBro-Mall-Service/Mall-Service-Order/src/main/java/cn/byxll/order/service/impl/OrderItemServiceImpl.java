package cn.byxll.order.service.impl;

import cn.byxll.order.dao.OrderItemMapper;
import cn.byxll.order.pojo.OrderItem;
import cn.byxll.order.service.OrderItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * OrderItem业务层接口实现类
 * @author By-Lin
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemMapper orderItemMapper;

    public OrderItemServiceImpl(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    /**
     * 新增OrderItem
     * @param orderItem      OrderItem实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(OrderItem orderItem){
        if(orderItem == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = orderItemMapper.insert(orderItem);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(String id){
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = orderItemMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改OrderItem
     * @param orderItem      OrderItem实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(OrderItem orderItem){
        if(orderItem == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = orderItemMapper.updateByPrimaryKey(orderItem);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询OrderItem
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<OrderItem> findById(String id) {
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        OrderItem orderItem = orderItemMapper.selectByPrimaryKey(id);
        if(orderItem != null) { return new Result<>(true, StatusCode.OK, "查询成功", orderItem); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有OrderItem
     * @return      响应数据
     */
    @Override
    public Result<List<OrderItem>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", orderItemMapper.selectAll());
    }

    /**
     * OrderItem分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<OrderItem>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(orderItemMapper.selectAll()));
    }


    /**
     * OrderItem条件分页查询
     * @param orderItem      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<OrderItem>> findPagerByParam(OrderItem orderItem, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(orderItem);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<OrderItem>(orderItemMapper.selectByExample(example)));
    }

    /**
     * OrderItem多条件搜索方法
     * @param orderItem      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<OrderItem>> findList(OrderItem orderItem){
        if(orderItem == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(orderItem);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<OrderItem>(orderItemMapper.selectByExample(example)));
    }


    /**
     * OrderItem构建查询对象
     * @param orderItem      OrderItem实体
     * @return              查询对象
     */
    private Example createExample(OrderItem orderItem){
        Example example = new Example(OrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        if(orderItem!=null){
            // ID
            if(!StringUtils.isEmpty(orderItem.getId())){
                criteria.andEqualTo("id",orderItem.getId());
            }
            // 1级分类
            if(!StringUtils.isEmpty(orderItem.getCategoryId1())){
                criteria.andEqualTo("categoryId1",orderItem.getCategoryId1());
            }
            // 2级分类
            if(!StringUtils.isEmpty(orderItem.getCategoryId2())){
                criteria.andEqualTo("categoryId2",orderItem.getCategoryId2());
            }
            // 3级分类
            if(!StringUtils.isEmpty(orderItem.getCategoryId3())){
                criteria.andEqualTo("categoryId3",orderItem.getCategoryId3());
            }
            // SPU_ID
            if(!StringUtils.isEmpty(orderItem.getSpuId())){
                criteria.andEqualTo("spuId",orderItem.getSpuId());
            }
            // SKU_ID
            if(!StringUtils.isEmpty(orderItem.getSkuId())){
                criteria.andEqualTo("skuId",orderItem.getSkuId());
            }
            // 订单ID
            if(!StringUtils.isEmpty(orderItem.getOrderId())){
                criteria.andEqualTo("orderId",orderItem.getOrderId());
            }
            // 商品名称
            if(!StringUtils.isEmpty(orderItem.getName())){
                criteria.andLike("name","%"+orderItem.getName()+"%");
            }
            // 单价
            if(!StringUtils.isEmpty(orderItem.getPrice())){
                criteria.andEqualTo("price",orderItem.getPrice());
            }
            // 数量
            if(!StringUtils.isEmpty(orderItem.getNum())){
                criteria.andEqualTo("num",orderItem.getNum());
            }
            // 总金额
            if(!StringUtils.isEmpty(orderItem.getMoney())){
                criteria.andEqualTo("money",orderItem.getMoney());
            }
            // 实付金额
            if(!StringUtils.isEmpty(orderItem.getPayMoney())){
                criteria.andEqualTo("payMoney",orderItem.getPayMoney());
            }
            // 图片地址
            if(!StringUtils.isEmpty(orderItem.getImage())){
                criteria.andEqualTo("image",orderItem.getImage());
            }
            // 重量
            if(!StringUtils.isEmpty(orderItem.getWeight())){
                criteria.andEqualTo("weight",orderItem.getWeight());
            }
            // 运费
            if(!StringUtils.isEmpty(orderItem.getPostFee())){
                criteria.andEqualTo("postFee",orderItem.getPostFee());
            }
            // 是否退货
            if(!StringUtils.isEmpty(orderItem.getIsReturn())){
                criteria.andEqualTo("isReturn",orderItem.getIsReturn());
            }
        }
        return example;
    }

}
