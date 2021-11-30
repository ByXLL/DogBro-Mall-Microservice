package cn.byxll.order.controller;

import cn.byxll.order.pojo.ReturnOrder;
import cn.byxll.order.service.impl.ReturnOrderServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ReturnOrder 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/returnOrder")
public class ReturnOrderController {

    private final ReturnOrderServiceImpl returnOrderService;

    public ReturnOrderController(ReturnOrderServiceImpl returnOrderService) {
        this.returnOrderService = returnOrderService;
    }

    /**
     * 新增ReturnOrder数据
     * @param returnOrder      ReturnOrder实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody ReturnOrder returnOrder){
        return returnOrderService.add(returnOrder);
    }

    /**
     * 根据ID删除ReturnOrder数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") String id){
        return returnOrderService.delete(id);
    }

    /**
     * 修改ReturnOrder数据
     * @param returnOrder      ReturnOrder实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody ReturnOrder returnOrder){
        return returnOrderService.update(returnOrder);
    }

    /**
     * 根据ID查询ReturnOrder数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<ReturnOrder> findById(@PathVariable("id") String id){
        return returnOrderService.findById(id);
    }

    /**
     * 查询ReturnOrder全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<ReturnOrder>> findAll(){
        return returnOrderService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param returnOrder      ReturnOrder实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<ReturnOrder>> findList(@RequestBody ReturnOrder returnOrder){
        return returnOrderService.findList(returnOrder);
    }

    /**
     * ReturnOrder分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<ReturnOrder>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return returnOrderService.findByPager(page, pageSize);
    }


    /**
     * ReturnOrder分页条件搜索实现
     * @param returnOrder          ReturnOrder实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<ReturnOrder>> findPagerByParam(@RequestBody ReturnOrder returnOrder, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return returnOrderService.findPagerByParam(returnOrder, page, pageSize);
    }

}
