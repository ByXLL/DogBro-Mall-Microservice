package cn.byxll.seckill.service;

import cn.byxll.seckill.pojo.SeckillOrder;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * SeckillOrder业务层接口类
 * @author @By-Lin
 */
public interface SeckillOrderService {

    /**
     * 新增SeckillOrder
     * @param   seckillOrder      SeckillOrder实体
     * @return                 响应数据
     */
    Result<Boolean> add(SeckillOrder seckillOrder);

    /**
     * 通过组件删除SeckillOrder
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(Long id);

    /**
     * 修改SeckillOrder数据
     * @param seckillOrder      SeckillOrder实体
     * @return              响应数据
     */
    Result<Boolean> update(SeckillOrder seckillOrder);

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
}
