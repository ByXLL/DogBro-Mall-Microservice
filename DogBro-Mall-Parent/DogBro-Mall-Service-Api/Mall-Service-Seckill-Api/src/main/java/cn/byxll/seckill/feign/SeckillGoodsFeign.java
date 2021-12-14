package cn.byxll.seckill.feign;
import cn.byxll.seckill.pojo.SeckillGoods;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SeckillGoods feign
 * @author  By-Lin
 */
//@RequestMapping("/seckillGoods")
//@FeignClient(name="seckill")
public interface SeckillGoodsFeign {

    /**
     * 新增SeckillGoods数据
     * @param seckillGoods      SeckillGoods实体
     * @return              响应数据
     */
    @PostMapping("/add")
    Result<Boolean> add(@RequestBody SeckillGoods seckillGoods);

    /**
     * 根据ID删除SeckillGoods数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    Result<Boolean> delete(@PathVariable("id") Long id);

    /**
     * 修改SeckillGoods数据
     * @param seckillGoods      SeckillGoods实体
     * @return              响应数据
     */
    @PostMapping("/update")
    Result<Boolean> update(@RequestBody SeckillGoods seckillGoods);

    /**
     * 根据ID查询SeckillGoods数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    Result<SeckillGoods> findById(@PathVariable("id") Long id);

    /**
     * 查询SeckillGoods全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    Result<List<SeckillGoods>> findAll();

    /**
     * 多条件搜索品牌数据
     * @param seckillGoods      SeckillGoods实体
     * @return              响应数据
     */
    @PostMapping("/search")
    Result<List<SeckillGoods>> findList(@RequestBody SeckillGoods seckillGoods);

    /**
     * SeckillGoods分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping("/search/{page}/{pageSize}")
    Result<PageInfo<SeckillGoods>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize);

    /**
     * SeckillGoods分页条件搜索实现
     * @param seckillGoods          SeckillGoods实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping("/search/{page}/{pageSize}")
    Result<PageInfo<SeckillGoods>> findPagerByParam(@RequestBody SeckillGoods seckillGoods, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize);
}