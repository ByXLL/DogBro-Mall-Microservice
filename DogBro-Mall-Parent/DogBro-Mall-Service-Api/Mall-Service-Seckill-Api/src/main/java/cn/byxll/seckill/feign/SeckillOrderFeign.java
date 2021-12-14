package cn.byxll.seckill.feign;
import cn.byxll.seckill.pojo.SeckillOrder;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SeckillOrder feign
 * @author  By-Lin
 */
//@RequestMapping("/seckillOrder")
//@FeignClient(name="seckill")
public interface SeckillOrderFeign {

    /**
     * 新增SeckillOrder数据
     * @param seckillOrder      SeckillOrder实体
     * @return              响应数据
     */
    @PostMapping("/add")
    Result<Boolean> add(@RequestBody SeckillOrder seckillOrder);

    /**
     * 根据ID删除SeckillOrder数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    Result<Boolean> delete(@PathVariable("id") Long id);

    /**
     * 修改SeckillOrder数据
     * @param seckillOrder      SeckillOrder实体
     * @return              响应数据
     */
    @PostMapping("/update")
    Result<Boolean> update(@RequestBody SeckillOrder seckillOrder);

    /**
     * 根据ID查询SeckillOrder数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    Result<SeckillOrder> findById(@PathVariable("id") Long id);

    /**
     * 查询SeckillOrder全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    Result<List<SeckillOrder>> findAll();

    /**
     * 多条件搜索品牌数据
     * @param seckillOrder      SeckillOrder实体
     * @return              响应数据
     */
    @PostMapping("/search")
    Result<List<SeckillOrder>> findList(@RequestBody SeckillOrder seckillOrder);

    /**
     * SeckillOrder分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping("/search/{page}/{pageSize}")
    Result<PageInfo<SeckillOrder>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize);

    /**
     * SeckillOrder分页条件搜索实现
     * @param seckillOrder          SeckillOrder实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping("/search/{page}/{pageSize}")
    Result<PageInfo<SeckillOrder>> findPagerByParam(@RequestBody SeckillOrder seckillOrder, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize);
}