package cn.byxll.order.controller;

import cn.byxll.order.pojo.OrderLog;
import cn.byxll.order.service.impl.OrderLogServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OrderLog 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/orderLog")
public class OrderLogController {

    private final OrderLogServiceImpl orderLogService;

    public OrderLogController(OrderLogServiceImpl orderLogService) {
        this.orderLogService = orderLogService;
    }

    /**
     * 新增OrderLog数据
     * @param orderLog      OrderLog实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody OrderLog orderLog){
        return orderLogService.add(orderLog);
    }

    /**
     * 根据ID删除OrderLog数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") String id){
        return orderLogService.delete(id);
    }

    /**
     * 修改OrderLog数据
     * @param orderLog      OrderLog实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody OrderLog orderLog){
        return orderLogService.update(orderLog);
    }

    /**
     * 根据ID查询OrderLog数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<OrderLog> findById(@PathVariable("id") String id){
        return orderLogService.findById(id);
    }

    /**
     * 查询OrderLog全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<OrderLog>> findAll(){
        return orderLogService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param orderLog      OrderLog实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<OrderLog>> findList(@RequestBody OrderLog orderLog){
        return orderLogService.findList(orderLog);
    }

    /**
     * OrderLog分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<OrderLog>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return orderLogService.findByPager(page, pageSize);
    }


    /**
     * OrderLog分页条件搜索实现
     * @param orderLog          OrderLog实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<OrderLog>> findPagerByParam(@RequestBody OrderLog orderLog, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return orderLogService.findPagerByParam(orderLog, page, pageSize);
    }

}
