package cn.byxll.user.controller;

import cn.byxll.user.pojo.Cities;
import cn.byxll.user.service.impl.CitiesServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Cities 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/cities")
public class CitiesController {

    private final CitiesServiceImpl citiesService;

    public CitiesController(CitiesServiceImpl citiesService) {
        this.citiesService = citiesService;
    }

    /**
     * 新增Cities数据
     * @param cities      Cities实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Cities cities){
        return citiesService.add(cities);
    }

    /**
     * 根据ID删除Cities数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") String id){
        return citiesService.delete(id);
    }

    /**
     * 修改Cities数据
     * @param cities      Cities实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Cities cities){
        return citiesService.update(cities);
    }

    /**
     * 根据ID查询Cities数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<Cities> findById(@PathVariable("id") String id){
        return citiesService.findById(id);
    }

    /**
     * 查询Cities全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<Cities>> findAll(){
        return citiesService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param cities      Cities实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<Cities>> findList(@RequestBody Cities cities){
        return citiesService.findList(cities);
    }

    /**
     * Cities分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<Cities>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return citiesService.findByPager(page, pageSize);
    }


    /**
     * Cities分页条件搜索实现
     * @param cities          Cities实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<Cities>> findPagerByParam(@RequestBody Cities cities, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return citiesService.findPagerByParam(cities, page, pageSize);
    }

}
