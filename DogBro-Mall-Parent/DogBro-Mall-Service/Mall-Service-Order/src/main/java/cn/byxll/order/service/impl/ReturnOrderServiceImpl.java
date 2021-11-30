package cn.byxll.order.service.impl;

import cn.byxll.order.dao.ReturnOrderMapper;
import cn.byxll.order.pojo.ReturnOrder;
import cn.byxll.order.service.ReturnOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * ReturnOrder业务层接口实现类
 * @author By-Lin
 */
@Service
public class ReturnOrderServiceImpl implements ReturnOrderService {

    private final ReturnOrderMapper returnOrderMapper;

    public ReturnOrderServiceImpl(ReturnOrderMapper returnOrderMapper) {
        this.returnOrderMapper = returnOrderMapper;
    }

    /**
     * 新增ReturnOrder
     * @param returnOrder      ReturnOrder实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(ReturnOrder returnOrder){
        if(returnOrder == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = returnOrderMapper.insert(returnOrder);
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
        int i = returnOrderMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改ReturnOrder
     * @param returnOrder      ReturnOrder实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(ReturnOrder returnOrder){
        if(returnOrder == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = returnOrderMapper.updateByPrimaryKey(returnOrder);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询ReturnOrder
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<ReturnOrder> findById(String id) {
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        ReturnOrder returnOrder = returnOrderMapper.selectByPrimaryKey(id);
        if(returnOrder != null) { return new Result<>(true, StatusCode.OK, "查询成功", returnOrder); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有ReturnOrder
     * @return      响应数据
     */
    @Override
    public Result<List<ReturnOrder>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", returnOrderMapper.selectAll());
    }

    /**
     * ReturnOrder分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<ReturnOrder>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(returnOrderMapper.selectAll()));
    }


    /**
     * ReturnOrder条件分页查询
     * @param returnOrder      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<ReturnOrder>> findPagerByParam(ReturnOrder returnOrder, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(returnOrder);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<ReturnOrder>(returnOrderMapper.selectByExample(example)));
    }

    /**
     * ReturnOrder多条件搜索方法
     * @param returnOrder      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<ReturnOrder>> findList(ReturnOrder returnOrder){
        if(returnOrder == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(returnOrder);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<ReturnOrder>(returnOrderMapper.selectByExample(example)));
    }


    /**
     * ReturnOrder构建查询对象
     * @param returnOrder      ReturnOrder实体
     * @return              查询对象
     */
    private Example createExample(ReturnOrder returnOrder){
        Example example = new Example(ReturnOrder.class);
        Example.Criteria criteria = example.createCriteria();
        if(returnOrder!=null){
            // 服务单号
            if(!StringUtils.isEmpty(returnOrder.getId())){
                criteria.andEqualTo("id",returnOrder.getId());
            }
            // 订单号
            if(!StringUtils.isEmpty(returnOrder.getOrderId())){
                criteria.andEqualTo("orderId",returnOrder.getOrderId());
            }
            // 申请时间
            if(!StringUtils.isEmpty(returnOrder.getApplyTime())){
                criteria.andEqualTo("applyTime",returnOrder.getApplyTime());
            }
            // 用户ID
            if(!StringUtils.isEmpty(returnOrder.getUserId())){
                criteria.andEqualTo("userId",returnOrder.getUserId());
            }
            // 用户账号
            if(!StringUtils.isEmpty(returnOrder.getUserAccount())){
                criteria.andEqualTo("userAccount",returnOrder.getUserAccount());
            }
            // 联系人
            if(!StringUtils.isEmpty(returnOrder.getLinkman())){
                criteria.andEqualTo("linkman",returnOrder.getLinkman());
            }
            // 联系人手机
            if(!StringUtils.isEmpty(returnOrder.getLinkmanMobile())){
                criteria.andEqualTo("linkmanMobile",returnOrder.getLinkmanMobile());
            }
            // 类型
            if(!StringUtils.isEmpty(returnOrder.getType())){
                criteria.andEqualTo("type",returnOrder.getType());
            }
            // 退款金额
            if(!StringUtils.isEmpty(returnOrder.getReturnMoney())){
                criteria.andEqualTo("returnMoney",returnOrder.getReturnMoney());
            }
            // 是否退运费
            if(!StringUtils.isEmpty(returnOrder.getIsReturnFreight())){
                criteria.andEqualTo("isReturnFreight",returnOrder.getIsReturnFreight());
            }
            // 申请状态
            if(!StringUtils.isEmpty(returnOrder.getStatus())){
                criteria.andEqualTo("status",returnOrder.getStatus());
            }
            // 处理时间
            if(!StringUtils.isEmpty(returnOrder.getDisposeTime())){
                criteria.andEqualTo("disposeTime",returnOrder.getDisposeTime());
            }
            // 退货退款原因
            if(!StringUtils.isEmpty(returnOrder.getReturnCause())){
                criteria.andEqualTo("returnCause",returnOrder.getReturnCause());
            }
            // 凭证图片
            if(!StringUtils.isEmpty(returnOrder.getEvidence())){
                criteria.andEqualTo("evidence",returnOrder.getEvidence());
            }
            // 问题描述
            if(!StringUtils.isEmpty(returnOrder.getDescription())){
                criteria.andEqualTo("description",returnOrder.getDescription());
            }
            // 处理备注
            if(!StringUtils.isEmpty(returnOrder.getRemark())){
                criteria.andEqualTo("remark",returnOrder.getRemark());
            }
            // 管理员id
            if(!StringUtils.isEmpty(returnOrder.getAdminId())){
                criteria.andEqualTo("adminId",returnOrder.getAdminId());
            }
        }
        return example;
    }

}
