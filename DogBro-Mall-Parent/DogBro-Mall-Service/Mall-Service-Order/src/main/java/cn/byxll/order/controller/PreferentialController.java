package cn.byxll.order.controller;

import cn.byxll.order.pojo.Preferential;
import cn.byxll.order.service.impl.PreferentialServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Preferential 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/preferential")
public class PreferentialController {

    private final PreferentialServiceImpl preferentialService;

    public PreferentialController(PreferentialServiceImpl preferentialService) {
        this.preferentialService = preferentialService;
    }

    /**
     * 新增Preferential数据
     * @param preferential      Preferential实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Preferential preferential){
        return preferentialService.add(preferential);
    }

    /**
     * 根据ID删除Preferential数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") Integer id){
        return preferentialService.delete(id);
    }

    /**
     * 修改Preferential数据
     * @param preferential      Preferential实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Preferential preferential){
        return preferentialService.update(preferential);
    }

    /**
     * 根据ID查询Preferential数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<Preferential> findById(@PathVariable("id") Integer id){
        return preferentialService.findById(id);
    }

    /**
     * 查询Preferential全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<Preferential>> findAll(){
        return preferentialService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param preferential      Preferential实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<Preferential>> findList(@RequestBody Preferential preferential){
        return preferentialService.findList(preferential);
    }

    /**
     * Preferential分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<Preferential>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return preferentialService.findByPager(page, pageSize);
    }


    /**
     * Preferential分页条件搜索实现
     * @param preferential          Preferential实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<Preferential>> findPagerByParam(@RequestBody Preferential preferential, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return preferentialService.findPagerByParam(preferential, page, pageSize);
    }

}
