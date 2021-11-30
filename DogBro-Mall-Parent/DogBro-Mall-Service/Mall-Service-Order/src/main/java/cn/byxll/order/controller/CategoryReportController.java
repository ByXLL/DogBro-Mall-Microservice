package cn.byxll.order.controller;

import cn.byxll.order.pojo.CategoryReport;
import cn.byxll.order.service.impl.CategoryReportServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * CategoryReport 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/categoryReport")
public class CategoryReportController {

    private final CategoryReportServiceImpl categoryReportService;

    public CategoryReportController(CategoryReportServiceImpl categoryReportService) {
        this.categoryReportService = categoryReportService;
    }

    /**
     * 新增CategoryReport数据
     * @param categoryReport      CategoryReport实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody CategoryReport categoryReport){
        return categoryReportService.add(categoryReport);
    }

    /**
     * 根据ID删除CategoryReport数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") Date id){
        return categoryReportService.delete(id);
    }

    /**
     * 修改CategoryReport数据
     * @param categoryReport      CategoryReport实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody CategoryReport categoryReport){
        return categoryReportService.update(categoryReport);
    }

    /**
     * 根据ID查询CategoryReport数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<CategoryReport> findById(@PathVariable("id") Date id){
        return categoryReportService.findById(id);
    }

    /**
     * 查询CategoryReport全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<CategoryReport>> findAll(){
        return categoryReportService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param categoryReport      CategoryReport实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<CategoryReport>> findList(@RequestBody CategoryReport categoryReport){
        return categoryReportService.findList(categoryReport);
    }

    /**
     * CategoryReport分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<CategoryReport>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return categoryReportService.findByPager(page, pageSize);
    }


    /**
     * CategoryReport分页条件搜索实现
     * @param categoryReport          CategoryReport实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<CategoryReport>> findPagerByParam(@RequestBody CategoryReport categoryReport, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return categoryReportService.findPagerByParam(categoryReport, page, pageSize);
    }

}
