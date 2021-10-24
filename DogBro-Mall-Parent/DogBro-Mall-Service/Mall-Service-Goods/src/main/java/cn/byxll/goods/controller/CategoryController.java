package cn.byxll.goods.controller;

import cn.byxll.goods.pojo.Category;
import cn.byxll.goods.service.impl.CategoryServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类 controller
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 添加商品分类
     * @param category      商品分类实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Category category) {
        return categoryService.add(category);
    }

    /**
     * 通过i删除商品分类
     * @param id    分类id
     * @return      响应数据
     */
    @PostMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") Integer id) {
        return categoryService.delete(id);
    }

    /**
     * 修改商品分类
     * @param category      商品分类实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Category category) {
       return categoryService.update(category);
    }

    /**
     * 根据id查询分类实体信息
     * @param id        商品分类id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable("id") Integer id) {
        return categoryService.findById(id);
    }

    /**
     * 分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @GetMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Category>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return categoryService.findByPager(page, pageSize);
    }

    /**
     * 按条件分页查询
     * @param category      分类实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @PostMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Category>> add(@RequestBody Category category, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return categoryService.findPagerByParam(category,page,pageSize);
    }

    /**
     * 根据pid查询分类列表
     * @param pId       pid
     * @return          响应数据
     */
    @GetMapping("/searchByPId/{pId}")
    public Result<List<Category>> findByParentId(@PathVariable("pId") Integer pId) {
        return categoryService.findByParentId(pId);
    }

    /**
     * 根据模板id查询分类列表
     * @param tId       模板
     * @return          响应数据
     */
    @GetMapping("/searchByTId/{tId}")
    public Result<List<Category>> findByTemplateId(@PathVariable("tId") Integer tId) {
        return categoryService.findByTemplateId(tId);
    }

    /**
     * 查询所有分类
     * @return      响应数据
     */
    @GetMapping("/list")
    public Result<List<Category>> findAll() {
        return categoryService.findAll();
    }
}
