package cn.byxll.order.controller;

import cn.byxll.order.pojo.OrderItem;
import cn.byxll.order.service.impl.OrderItemServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OrderItem 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    private final OrderItemServiceImpl orderItemService;

    public OrderItemController(OrderItemServiceImpl orderItemService) {
        this.orderItemService = orderItemService;
    }

    /**
     * 新增OrderItem数据
     * @param orderItem      OrderItem实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody OrderItem orderItem){
        return orderItemService.add(orderItem);
    }

    /**
     * 根据ID删除OrderItem数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") String id){
        return orderItemService.delete(id);
    }

    /**
     * 修改OrderItem数据
     * @param orderItem      OrderItem实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody OrderItem orderItem){
        return orderItemService.update(orderItem);
    }

    /**
     * 根据ID查询OrderItem数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<OrderItem> findById(@PathVariable("id") String id){
        return orderItemService.findById(id);
    }

    /**
     * 查询OrderItem全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<OrderItem>> findAll(){
        return orderItemService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param orderItem      OrderItem实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<OrderItem>> findList(@RequestBody OrderItem orderItem){
        return orderItemService.findList(orderItem);
    }

    /**
     * OrderItem分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<OrderItem>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return orderItemService.findByPager(page, pageSize);
    }


    /**
     * OrderItem分页条件搜索实现
     * @param orderItem          OrderItem实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<OrderItem>> findPagerByParam(@RequestBody OrderItem orderItem, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return orderItemService.findPagerByParam(orderItem, page, pageSize);
    }

}
