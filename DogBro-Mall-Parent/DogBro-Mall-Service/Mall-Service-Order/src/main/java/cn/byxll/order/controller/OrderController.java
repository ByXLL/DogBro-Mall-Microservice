package cn.byxll.order.controller;

import cn.byxll.order.dto.OrderDto;
import cn.byxll.order.pojo.Order;
import cn.byxll.order.service.impl.OrderServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Order 控制器类
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    /**
     * 新增Order数据
     * @param orderDto      Order dto 实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody OrderDto orderDto){
        return orderService.add(orderDto);
    }

    /**
     * 根据ID删除Order数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") String id){
        return orderService.delete(id);
    }

    /**
     * 修改Order数据
     * @param order      Order实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Order order){
        return orderService.update(order);
    }

    /**
     * 根据ID查询Order数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<Order> findById(@PathVariable("id") String id){
        return orderService.findById(id);
    }

    /**
     * 查询Order全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<Order>> findAll(){
        return orderService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param order      Order实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<Order>> findList(@RequestBody Order order){
        return orderService.findList(order);
    }

    /**
     * Order分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<Order>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return orderService.findByPager(page, pageSize);
    }


    /**
     * Order分页条件搜索实现
     * @param order          Order实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<Order>> findPagerByParam(@RequestBody Order order, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return orderService.findPagerByParam(order, page, pageSize);
    }

}
