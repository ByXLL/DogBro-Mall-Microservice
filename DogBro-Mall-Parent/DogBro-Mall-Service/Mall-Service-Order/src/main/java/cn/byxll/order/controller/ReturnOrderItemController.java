package cn.byxll.order.controller;

import cn.byxll.order.pojo.ReturnOrderItem;
import cn.byxll.order.service.impl.ReturnOrderItemServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ReturnOrderItem 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/returnOrderItem")
public class ReturnOrderItemController {

    private final ReturnOrderItemServiceImpl returnOrderItemService;

    public ReturnOrderItemController(ReturnOrderItemServiceImpl returnOrderItemService) {
        this.returnOrderItemService = returnOrderItemService;
    }

    /**
     * 新增ReturnOrderItem数据
     * @param returnOrderItem      ReturnOrderItem实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody ReturnOrderItem returnOrderItem){
        return returnOrderItemService.add(returnOrderItem);
    }

    /**
     * 根据ID删除ReturnOrderItem数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") String id){
        return returnOrderItemService.delete(id);
    }

    /**
     * 修改ReturnOrderItem数据
     * @param returnOrderItem      ReturnOrderItem实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody ReturnOrderItem returnOrderItem){
        return returnOrderItemService.update(returnOrderItem);
    }

    /**
     * 根据ID查询ReturnOrderItem数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<ReturnOrderItem> findById(@PathVariable("id") String id){
        return returnOrderItemService.findById(id);
    }

    /**
     * 查询ReturnOrderItem全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<ReturnOrderItem>> findAll(){
        return returnOrderItemService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param returnOrderItem      ReturnOrderItem实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<ReturnOrderItem>> findList(@RequestBody ReturnOrderItem returnOrderItem){
        return returnOrderItemService.findList(returnOrderItem);
    }

    /**
     * ReturnOrderItem分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<ReturnOrderItem>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return returnOrderItemService.findByPager(page, pageSize);
    }


    /**
     * ReturnOrderItem分页条件搜索实现
     * @param returnOrderItem          ReturnOrderItem实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<ReturnOrderItem>> findPagerByParam(@RequestBody ReturnOrderItem returnOrderItem, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return returnOrderItemService.findPagerByParam(returnOrderItem, page, pageSize);
    }

}
