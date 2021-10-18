package cn.byxll.controller;

import cn.byxll.goods.pojo.Sku;
import cn.byxll.service.impl.SkuServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Sku 控制器
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/sku")
public class SkuController {

    private final SkuServiceImpl skuService;

    public SkuController(SkuServiceImpl skuService) {
        this.skuService = skuService;
    }

    /***
     * 新增Sku数据
     * @param sku      sku实体
     * @return         响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Sku sku){
        return skuService.add(sku);
    }

    /**
     * 根据ID删除
     * @param id        主键id
     * @return          响应数据
     */
    @PostMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable String id){
        return skuService.delete(id);
    }

    /**
     * 修改Sku数据
     * @param sku      sku实体
     * @return         响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Sku sku){
        return skuService.update(sku);
    }


    /**
     * 根据ID查询Sku数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<Sku> findById(@PathVariable String id){
        return skuService.findById(id);
    }

    /**
     * Sku分页搜索实现
     * @param page       当前页
     * @param pageSize   每页大小
     * @return           响应数据
     */
    @GetMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Sku>> findByPager(@PathVariable  Integer page, @PathVariable  Integer pageSize){
        return skuService.findByPager(page, pageSize);
    }

    /**
     * Sku分页条件查询
     * @param sku           表实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @PostMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Sku>> findPagerByParam(@RequestBody(required = false)  Sku sku, @PathVariable  Integer page, @PathVariable  Integer pageSize){
        return skuService.findPagerByParam(sku, page, pageSize);
    }

    /***
     * 多条件搜索品牌数据
     * @param sku       sku实体
     * @return          响应数据
     */
    @PostMapping("/search")
    public Result<List<Sku>> findListByParam(@RequestBody(required = false)  Sku sku){
        return skuService.findListByParam(sku);
    }


    /***
     * 查询Sku全部数据
     * @return      响应数据
     */
    @GetMapping("/list")
    public Result<List<Sku>> findAll(){
        return skuService.findAll();
    }
}
