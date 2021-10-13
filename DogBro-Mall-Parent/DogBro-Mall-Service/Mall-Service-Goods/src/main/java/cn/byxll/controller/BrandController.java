package cn.byxll.controller;

import cn.byxll.goods.pojo.Brand;
import cn.byxll.service.impl.BrandServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌 controller
 * @author By-Lin
 */
@RestController
@CrossOrigin
@RequestMapping("/brand")
public class BrandController {
    private final BrandServiceImpl brandService;

    public BrandController(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    /**
     * 查询所有的品牌
     */
    @GetMapping
    public Result<List<Brand>> findAll() {
        return brandService.findAll();
    }

    /**
     * 通过id查询品牌
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable("id") Integer id) {
        return brandService.findById(id);
    }

    /**
     * 条件查询
     */
    @PostMapping("/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand) {
        return brandService.findByEntity(brand);
    }

    /**
     * 分页查询
     */
    @GetMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Brand>> findPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return brandService.findByPager(page, pageSize);
    }

    /**
     * 条件分页查询
     */
    @PostMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Brand>> findPagerParam(@RequestBody Brand brand, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return brandService.findByPagerParam(brand, page, pageSize);
    }

    /**
     * 添加品牌
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Brand brand) {
        return brandService.add(brand);
    }

    /**
     * 修改品牌
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Brand brand) {
        return brandService.update(brand);
    }

    /**
     * 删除品牌
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable("id") Integer id) {
        return brandService.delete(id);
    }
}
