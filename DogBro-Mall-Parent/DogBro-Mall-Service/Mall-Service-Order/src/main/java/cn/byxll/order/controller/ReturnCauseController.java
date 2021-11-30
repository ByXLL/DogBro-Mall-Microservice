package cn.byxll.order.controller;

import cn.byxll.order.pojo.ReturnCause;
import cn.byxll.order.service.impl.ReturnCauseServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ReturnCause 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/returnCause")
public class ReturnCauseController {

    private final ReturnCauseServiceImpl returnCauseService;

    public ReturnCauseController(ReturnCauseServiceImpl returnCauseService) {
        this.returnCauseService = returnCauseService;
    }

    /**
     * 新增ReturnCause数据
     * @param returnCause      ReturnCause实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody ReturnCause returnCause){
        return returnCauseService.add(returnCause);
    }

    /**
     * 根据ID删除ReturnCause数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") Integer id){
        return returnCauseService.delete(id);
    }

    /**
     * 修改ReturnCause数据
     * @param returnCause      ReturnCause实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody ReturnCause returnCause){
        return returnCauseService.update(returnCause);
    }

    /**
     * 根据ID查询ReturnCause数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<ReturnCause> findById(@PathVariable("id") Integer id){
        return returnCauseService.findById(id);
    }

    /**
     * 查询ReturnCause全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<ReturnCause>> findAll(){
        return returnCauseService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param returnCause      ReturnCause实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<ReturnCause>> findList(@RequestBody ReturnCause returnCause){
        return returnCauseService.findList(returnCause);
    }

    /**
     * ReturnCause分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<ReturnCause>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return returnCauseService.findByPager(page, pageSize);
    }


    /**
     * ReturnCause分页条件搜索实现
     * @param returnCause          ReturnCause实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<ReturnCause>> findPagerByParam(@RequestBody ReturnCause returnCause, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return returnCauseService.findPagerByParam(returnCause, page, pageSize);
    }

}
