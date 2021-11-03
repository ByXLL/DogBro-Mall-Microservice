package cn.byxll.user.controller;

import cn.byxll.user.pojo.Provinces;
import cn.byxll.user.service.impl.ProvincesServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Provinces 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/provinces")
public class ProvincesController {

    private final ProvincesServiceImpl provincesService;

    public ProvincesController(ProvincesServiceImpl provincesService) {
        this.provincesService = provincesService;
    }

    /**
     * 新增Provinces数据
     * @param provinces      Provinces实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Provinces provinces){
        return provincesService.add(provinces);
    }

    /**
     * 根据ID删除Provinces数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") String id){
        return provincesService.delete(id);
    }

    /**
     * 修改Provinces数据
     * @param provinces      Provinces实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Provinces provinces){
        return provincesService.update(provinces);
    }

    /**
     * 根据ID查询Provinces数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<Provinces> findById(@PathVariable("id") String id){
        return provincesService.findById(id);
    }

    /**
     * 查询Provinces全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<Provinces>> findAll(){
        return provincesService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param provinces      Provinces实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<Provinces>> findList(@RequestBody Provinces provinces){
        return provincesService.findList(provinces);
    }

    /**
     * Provinces分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<Provinces>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return provincesService.findByPager(page, pageSize);
    }


    /**
     * Provinces分页条件搜索实现
     * @param provinces          Provinces实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<Provinces>> findPagerByParam(@RequestBody Provinces provinces, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return provincesService.findPagerByParam(provinces, page, pageSize);
    }

}
