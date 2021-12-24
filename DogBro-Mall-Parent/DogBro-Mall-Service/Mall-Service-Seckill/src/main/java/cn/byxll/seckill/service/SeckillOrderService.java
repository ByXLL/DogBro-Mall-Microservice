package cn.byxll.seckill.service;

import cn.byxll.seckill.pojo.SeckillOrder;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.text.ParseException;
import java.util.List;

/**
 * SeckillOrder业务层接口类
 * @author @By-Lin
 */
public interface SeckillOrderService {

    /**
     * 添加秒杀订单
     * @param time          当前时间
     * @param id            秒杀商品id
     * @param userName      用户名
     * @return              响应数据
     */
    Result<Boolean> add(String time, Long id, String userName);

    /**
     * 通过组件删除SeckillOrder
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(Long id);

    /**
     * 通过用户名删除订单
     * @param userName         用户名
     * @return                 响应数据
     */
    Result<Boolean> deleteOrderByUserName(String userName);

    /**
     * 修改SeckillOrder数据
     * @param seckillOrder      SeckillOrder实体
     * @return              响应数据
     */
    Result<Boolean> update(SeckillOrder seckillOrder);

    /**
     * 修改秒杀订单 支付状态
     * @param userName           用户名
     * @param transactionId      交易流水号
     * @param payTime            支付时间
     * @return             响应数据
     */
    Result<Boolean> updatePayStatus(String userName, String transactionId, String payTime) throws ParseException;

    /**
     * 根据ID查询SeckillOrder
     * @param id        主键id
     * @return          响应数据
     */
    Result<SeckillOrder> findById(Long id);

    /**
     * 查询所有SeckillOrder
     * @return          响应数据
     */
    Result<List<SeckillOrder>> findAll();

    /**
     * SeckillOrder分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<SeckillOrder>> findByPager(Integer page, Integer pageSize);

    /**
     * SeckillOrder条件分页查询
     * @param seckillOrder      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<SeckillOrder>> findPagerByParam(SeckillOrder seckillOrder, Integer page, Integer pageSize);
    

    /**
     * SeckillOrder多条件搜索方法
     * @param seckillOrder      条件实体
     * @return              响应数据
     */
    Result<List<SeckillOrder>> findList(SeckillOrder seckillOrder);

    /**
     * 订单状态查询
     * @return              响应数据
     */
    Result<SeckillOrder> queryStatus();
}
