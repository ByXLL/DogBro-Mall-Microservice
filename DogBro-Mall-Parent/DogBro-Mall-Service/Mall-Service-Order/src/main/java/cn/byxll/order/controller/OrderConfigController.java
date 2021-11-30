package cn.byxll.order.controller;

import cn.byxll.order.pojo.OrderConfig;
import cn.byxll.order.service.impl.OrderConfigServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OrderConfig 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/orderConfig")
public class OrderConfigController {

    private final OrderConfigServiceImpl orderConfigService;

    public OrderConfigController(OrderConfigServiceImpl orderConfigService) {
        this.orderConfigService = orderConfigService;
    }

    /**
     * 新增OrderConfig数据
     * @param orderConfig      OrderConfig实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody OrderConfig orderConfig){
        return orderConfigService.add(orderConfig);
    }

    /**
     * 根据ID删除OrderConfig数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") Integer id){
        return orderConfigService.delete(id);
    }

    /**
     * 修改OrderConfig数据
     * @param orderConfig      OrderConfig实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody OrderConfig orderConfig){
        return orderConfigService.update(orderConfig);
    }

    /**
     * 根据ID查询OrderConfig数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<OrderConfig> findById(@PathVariable("id") Integer id){
        return orderConfigService.findById(id);
    }

    /**
     * 查询OrderConfig全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<OrderConfig>> findAll(){
        return orderConfigService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param orderConfig      OrderConfig实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<OrderConfig>> findList(@RequestBody OrderConfig orderConfig){
        return orderConfigService.findList(orderConfig);
    }

    /**
     * OrderConfig分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<OrderConfig>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return orderConfigService.findByPager(page, pageSize);
    }


    /**
     * OrderConfig分页条件搜索实现
     * @param orderConfig          OrderConfig实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<OrderConfig>> findPagerByParam(@RequestBody OrderConfig orderConfig, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return orderConfigService.findPagerByParam(orderConfig, page, pageSize);
    }

}
