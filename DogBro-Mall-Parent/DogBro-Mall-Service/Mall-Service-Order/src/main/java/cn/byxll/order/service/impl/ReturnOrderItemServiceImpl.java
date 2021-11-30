package cn.byxll.order.service.impl;

import cn.byxll.order.dao.ReturnOrderItemMapper;
import cn.byxll.order.pojo.ReturnOrderItem;
import cn.byxll.order.service.ReturnOrderItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * ReturnOrderItem业务层接口实现类
 * @author By-Lin
 */
@Service
public class ReturnOrderItemServiceImpl implements ReturnOrderItemService {

    private final ReturnOrderItemMapper returnOrderItemMapper;

    public ReturnOrderItemServiceImpl(ReturnOrderItemMapper returnOrderItemMapper) {
        this.returnOrderItemMapper = returnOrderItemMapper;
    }

    /**
     * 新增ReturnOrderItem
     * @param returnOrderItem      ReturnOrderItem实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(ReturnOrderItem returnOrderItem){
        if(returnOrderItem == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = returnOrderItemMapper.insert(returnOrderItem);
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
        int i = returnOrderItemMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改ReturnOrderItem
     * @param returnOrderItem      ReturnOrderItem实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(ReturnOrderItem returnOrderItem){
        if(returnOrderItem == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = returnOrderItemMapper.updateByPrimaryKey(returnOrderItem);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询ReturnOrderItem
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<ReturnOrderItem> findById(String id) {
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        ReturnOrderItem returnOrderItem = returnOrderItemMapper.selectByPrimaryKey(id);
        if(returnOrderItem != null) { return new Result<>(true, StatusCode.OK, "查询成功", returnOrderItem); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有ReturnOrderItem
     * @return      响应数据
     */
    @Override
    public Result<List<ReturnOrderItem>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", returnOrderItemMapper.selectAll());
    }

    /**
     * ReturnOrderItem分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<ReturnOrderItem>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(returnOrderItemMapper.selectAll()));
    }


    /**
     * ReturnOrderItem条件分页查询
     * @param returnOrderItem      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<ReturnOrderItem>> findPagerByParam(ReturnOrderItem returnOrderItem, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(returnOrderItem);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<ReturnOrderItem>(returnOrderItemMapper.selectByExample(example)));
    }

    /**
     * ReturnOrderItem多条件搜索方法
     * @param returnOrderItem      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<ReturnOrderItem>> findList(ReturnOrderItem returnOrderItem){
        if(returnOrderItem == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(returnOrderItem);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<ReturnOrderItem>(returnOrderItemMapper.selectByExample(example)));
    }


    /**
     * ReturnOrderItem构建查询对象
     * @param returnOrderItem      ReturnOrderItem实体
     * @return              查询对象
     */
    private Example createExample(ReturnOrderItem returnOrderItem){
        Example example = new Example(ReturnOrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        if(returnOrderItem!=null){
            // ID
            if(!StringUtils.isEmpty(returnOrderItem.getId())){
                criteria.andEqualTo("id",returnOrderItem.getId());
            }
            // 分类ID
            if(!StringUtils.isEmpty(returnOrderItem.getCategoryId())){
                criteria.andEqualTo("categoryId",returnOrderItem.getCategoryId());
            }
            // SPU_ID
            if(!StringUtils.isEmpty(returnOrderItem.getSpuId())){
                criteria.andEqualTo("spuId",returnOrderItem.getSpuId());
            }
            // SKU_ID
            if(!StringUtils.isEmpty(returnOrderItem.getSkuId())){
                criteria.andEqualTo("skuId",returnOrderItem.getSkuId());
            }
            // 订单ID
            if(!StringUtils.isEmpty(returnOrderItem.getOrderId())){
                criteria.andEqualTo("orderId",returnOrderItem.getOrderId());
            }
            // 订单明细ID
            if(!StringUtils.isEmpty(returnOrderItem.getOrderItemId())){
                criteria.andEqualTo("orderItemId",returnOrderItem.getOrderItemId());
            }
            // 退货订单ID
            if(!StringUtils.isEmpty(returnOrderItem.getReturnOrderId())){
                criteria.andEqualTo("returnOrderId",returnOrderItem.getReturnOrderId());
            }
            // 标题
            if(!StringUtils.isEmpty(returnOrderItem.getTitle())){
                criteria.andLike("title","%"+returnOrderItem.getTitle()+"%");
            }
            // 单价
            if(!StringUtils.isEmpty(returnOrderItem.getPrice())){
                criteria.andEqualTo("price",returnOrderItem.getPrice());
            }
            // 数量
            if(!StringUtils.isEmpty(returnOrderItem.getNum())){
                criteria.andEqualTo("num",returnOrderItem.getNum());
            }
            // 总金额
            if(!StringUtils.isEmpty(returnOrderItem.getMoney())){
                criteria.andEqualTo("money",returnOrderItem.getMoney());
            }
            // 支付金额
            if(!StringUtils.isEmpty(returnOrderItem.getPayMoney())){
                criteria.andEqualTo("payMoney",returnOrderItem.getPayMoney());
            }
            // 图片地址
            if(!StringUtils.isEmpty(returnOrderItem.getImage())){
                criteria.andEqualTo("image",returnOrderItem.getImage());
            }
            // 重量
            if(!StringUtils.isEmpty(returnOrderItem.getWeight())){
                criteria.andEqualTo("weight",returnOrderItem.getWeight());
            }
        }
        return example;
    }

}
