package cn.byxll.seckill.service;

import cn.byxll.seckill.pojo.SeckillGoods;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.Date;
import java.util.List;

/**
 * 秒杀商品 业务层接口类
 * @author @By-Lin
 */
public interface SeckillGoodsService {

    /**
     * 新增SeckillGoods
     * @param   seckillGoods      SeckillGoods实体
     * @return                 响应数据
     */
    Result<Boolean> add(SeckillGoods seckillGoods);

    /**
     * 通过组件删除SeckillGoods
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(Long id);

    /**
     * 修改SeckillGoods数据
     * @param seckillGoods      SeckillGoods实体
     * @return              响应数据
     */
    Result<Boolean> update(SeckillGoods seckillGoods);

    /**
     * 根据ID查询SeckillGoods
     * @param id        主键id
     * @return          响应数据
     */
    Result<SeckillGoods> findById(Long id);

    /**
     * 查询所有SeckillGoods
     * @return          响应数据
     */
    Result<List<SeckillGoods>> findAll();

    /**
     * SeckillGoods分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<SeckillGoods>> findByPager(Integer page, Integer pageSize);

    /**
     * SeckillGoods条件分页查询
     * @param seckillGoods      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<SeckillGoods>> findPagerByParam(SeckillGoods seckillGoods, Integer page, Integer pageSize);
    

    /**
     * SeckillGoods多条件搜索方法
     * @param seckillGoods      条件实体
     * @return              响应数据
     */
    Result<List<SeckillGoods>> findList(SeckillGoods seckillGoods);

    /**
     * 获取秒杀 menus
     * @return  响应数据
     */
    Result<List<Date>> findSeckillMenus();

    /**
     * 根据当前时间获取秒杀商品列表
     * @param time      当前时间
     * @return          响应数据
     */
    Result<List<SeckillGoods>> findNowSeckillGoodsList(String time);

    /**
     * 查询秒杀商品详情
     * @param time      当前秒杀时间段
     * @param id        秒杀商品id
     * @return          响应数据
     */
    Result<SeckillGoods> findOne(String time, Long id);
}
