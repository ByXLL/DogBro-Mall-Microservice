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
        List<Brand> brandList = brandService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", brandList);
    }

    /**
     * 通过id查询品牌
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable("id") Integer id) {
        Brand brand = brandService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", brand);
    }

    /**
     * 条件查询
     */
    @PostMapping("/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand) {
        List<Brand> brandList = brandService.findByEntity(brand);
        return new Result<>(true, StatusCode.OK, "数据查询成功", brandList);
    }

    /**
     * 分页查询
     */
    @GetMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Brand>> findPage(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        PageInfo<Brand> brandPageInfo = brandService.findByPager(page, pageSize);
        return new Result<>(true,StatusCode.OK,"查询成功",brandPageInfo);
    }

    /**
     * 条件分页查询
     */
    @PostMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Brand>> findPage(@RequestBody Brand brand, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        PageInfo<Brand> brandPageInfo = brandService.findByPagerEntity(brand, page, pageSize);
        return new Result<>(true,StatusCode.OK,"查询成功",brandPageInfo);
    }

    /**
     * 添加品牌
     */
    @PostMapping("/add")
    public Result<Object> add(@RequestBody Brand brand) {
        brandService.add(brand);
        return new Result<>(true,StatusCode.OK,"添加成功");
    }

    /**
     * 修改品牌
     */
    @PostMapping("/update")
    public Result<Object> update(@RequestBody Brand brand) {
        brandService.update(brand);
        return new Result<>(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除品牌
     */
    @DeleteMapping("/{id}")
    public Result<Object> delete(@PathVariable("id") Integer id) {
        brandService.delete(id);
        return new Result<>(true,StatusCode.OK,"删除成功");
    }
}
