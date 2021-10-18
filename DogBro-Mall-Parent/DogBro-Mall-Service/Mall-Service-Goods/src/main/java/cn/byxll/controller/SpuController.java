package cn.byxll.controller;

import cn.byxll.goods.pojo.Spu;
import cn.byxll.service.impl.SpuServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Spu 控制器
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/spu")
public class SpuController {

    private final SpuServiceImpl spuService;

    public SpuController(SpuServiceImpl spuService) {
        this.spuService = spuService;
    }

    /**
     * 新增Spu数据
     * @param spu      spu实体
     * @return         响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Spu spu){
        return spuService.add(spu);
    }

    /**
     * 根据ID删除
     * @param id        主键id
     * @return          响应数据
     */
    @PostMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable String id){
        return spuService.delete(id);
    }

    /**
     * 修改Spu数据
     * @param spu      spu实体
     * @return         响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Spu spu){
        return spuService.update(spu);
    }


    /**
     * 根据ID查询Spu数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<Spu> findById(@PathVariable String id){
        return spuService.findById(id);
    }

    /**
     * Spu分页搜索实现
     * @param page          当前页
     * @param pageSize      每页显示多少条
     * @return              响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}")
    public Result<PageInfo<Spu>> findByPager(@PathVariable  Integer page, @PathVariable  Integer pageSize){
        return spuService.findByPager(page, pageSize);
    }

    /**
     * Spu分页条件查询
     * @param spu      表实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @PostMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Spu>> findPagerByParam(@RequestBody(required = false)  Spu spu, @PathVariable  Integer page, @PathVariable  Integer pageSize){
        return spuService.findPagerByParam(spu, page, pageSize);
    }

    /**
     * 多条件搜索品牌数据
     * @param spu       spu实体
     * @return          响应数据
     */
    @PostMapping("/search")
    public Result<List<Spu>> findListByParam(@RequestBody(required = false)  Spu spu){
        return spuService.findListByParam(spu);
    }

    /**
     * 查询Spu全部数据
     * @return      响应数据
     */
    @GetMapping("/list")
    public Result<List<Spu>> findAll(){
        return spuService.findAll();
    }
}
