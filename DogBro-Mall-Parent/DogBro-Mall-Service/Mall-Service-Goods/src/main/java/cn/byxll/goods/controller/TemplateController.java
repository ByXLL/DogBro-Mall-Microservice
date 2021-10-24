package cn.byxll.goods.controller;

import cn.byxll.goods.pojo.Template;
import cn.byxll.goods.service.impl.TemplateServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 模板 controller
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/template")
public class TemplateController {
    private final TemplateServiceImpl templateService;

    public TemplateController(TemplateServiceImpl templateService) {
        this.templateService = templateService;
    }

    /**
     * 添加模板
     * @param template      模板实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Template template) {
        return templateService.add(template);
    }

    /**
     * 删除模板
     * @param id        模板id
     * @return          响应数据
     */
    @PostMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") Integer id) {
        return templateService.delete(id);
    }

    /**
     * 修改模板
     * @param template      模板实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Template template) {
        return templateService.update(template);
    }

    /**
     * 查询所有
     * @return      响应数据
     */
    @GetMapping("/")
    public Result<List<Template>> findAll() {
        return templateService.findAll();
    }

    /**
     * 根据id查询模板
     * @param id    模板id
     * @return      响应数据
     */
    @GetMapping("/{id}")
    public Result<Template> findById(@PathVariable("id") Integer id) {
        return templateService.findById(id);
    }

    /**
     * 分页查询
     * @param page      当前页面
     * @param pageSize  每页大小
     * @return          响应数据
     */
    @GetMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Template>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return templateService.findPager(page, pageSize);
    }

    /**
     * 分页查询
     * @param template      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @PostMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Template>> findByPagerParam(@RequestBody Template template, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return templateService.findPagerByParam(template, page, pageSize);
    }
}
