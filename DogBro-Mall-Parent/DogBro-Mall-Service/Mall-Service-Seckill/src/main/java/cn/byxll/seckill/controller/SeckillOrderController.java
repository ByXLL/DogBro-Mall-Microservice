package cn.byxll.seckill.controller;

import cn.byxll.seckill.pojo.SeckillOrder;
import cn.byxll.seckill.service.impl.SeckillOrderServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SeckillOrder 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/seckillOrder")
public class SeckillOrderController {

    private final SeckillOrderServiceImpl seckillOrderService;

    public SeckillOrderController(SeckillOrderServiceImpl seckillOrderService) {
        this.seckillOrderService = seckillOrderService;
    }

    /**
     * 新增SeckillOrder数据
     * @param seckillOrder      SeckillOrder实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SeckillOrder seckillOrder){
        return seckillOrderService.add(seckillOrder);
    }

    /**
     * 根据ID删除SeckillOrder数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") Long id){
        return seckillOrderService.delete(id);
    }

    /**
     * 修改SeckillOrder数据
     * @param seckillOrder      SeckillOrder实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SeckillOrder seckillOrder){
        return seckillOrderService.update(seckillOrder);
    }

    /**
     * 根据ID查询SeckillOrder数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<SeckillOrder> findById(@PathVariable("id") Long id){
        return seckillOrderService.findById(id);
    }

    /**
     * 查询SeckillOrder全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<SeckillOrder>> findAll(){
        return seckillOrderService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param seckillOrder      SeckillOrder实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<SeckillOrder>> findList(@RequestBody SeckillOrder seckillOrder){
        return seckillOrderService.findList(seckillOrder);
    }

    /**
     * SeckillOrder分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping("/search/{page}/{pageSize}" )
    public Result<PageInfo<SeckillOrder>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return seckillOrderService.findByPager(page, pageSize);
    }


    /**
     * SeckillOrder分页条件搜索实现
     * @param seckillOrder          SeckillOrder实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping("/search/{page}/{pageSize}" )
    public Result<PageInfo<SeckillOrder>> findPagerByParam(@RequestBody SeckillOrder seckillOrder, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return seckillOrderService.findPagerByParam(seckillOrder, page, pageSize);
    }

}
