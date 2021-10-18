package cn.byxll.controller;

import cn.byxll.goods.pojo.Pref;
import cn.byxll.service.impl.PrefServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Pref 控制器
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/pref")
public class PrefController {

    private final PrefServiceImpl prefService;

    public PrefController(PrefServiceImpl prefService) {
        this.prefService = prefService;
    }

    /**
     * 新增Pref数据
     * @param pref      pref实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Pref pref){
        return prefService.add(pref);
    }

    /**
     * 根据ID删除
     * @param id        主键id
     * @return          响应数据
     */
    @PostMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable Integer id){
        return prefService.delete(id);
    }

    /**
     * 修改Pref数据
     * @param pref      pref实体
     * @return          响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody  Pref pref){
        return prefService.update(pref);
    }

    /**
     * 根据ID查询Pref数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<Pref> findById(@PathVariable Integer id){
        return prefService.findById(id);
    }

    /**
     * Pref分页条件查询
     * @param pref              表实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Pref>> findPagerByParam(@RequestBody(required = false)  Pref pref, @PathVariable  Integer page, @PathVariable  Integer pageSize){
        return prefService.findPagerByParam(pref, page, pageSize);
    }

    /**
     * Pref分页搜索实现
     * @param page          当前页
     * @param pageSize      每页显示多少条
     * @return              响应数据
     */
    @GetMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Pref>> findByPager(@PathVariable  Integer page, @PathVariable  Integer pageSize){
        return prefService.findByPager(page, pageSize);
    }

    /**
     * 多条件搜索品牌数据
     * @param pref      实体
     * @return          响应数据
     */
    @PostMapping("/search")
    public Result<List<Pref>> findListByParam(@RequestBody(required = false)  Pref pref){
        return prefService.findListByParam(pref);
    }

    /***
     * 查询Pref全部数据
     * @return      响应数据
     */
    @GetMapping
    public Result<List<Pref>> findAll(){
        return prefService.findAll();
    }
}
