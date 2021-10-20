package cn.byxll.controller;

import cn.byxll.goods.pojo.Spec;
import cn.byxll.service.impl.SpecServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 规格 controller
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/spec")
public class SpecController {
    private final SpecServiceImpl specService;

    public SpecController(SpecServiceImpl specService) {
        this.specService = specService;
    }

    /**
     * 添加规格
     * @param spec      规格实体
     * @return          响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Spec spec) {
        return specService.add(spec);
    }

    /**
     * 删除规格
     * @param id       规格id
     * @return         响应数据
     */
    @PostMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") Integer id) {
        return specService.delete(id);
    }

    /**
     * 编辑规格
     * @param spec     规格实体
     * @return         响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Spec spec) {
        return specService.update(spec);
    }

    /**
     * 通过id查询
     * @param id        规格id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<Spec> findById(@PathVariable("id") Integer id) {
        return specService.findById(id);
    }

    /**
     * 查询所有规格
     * @return      响应数据
     */
    @GetMapping("/list")
    public Result<List<Spec>> findAll() {
        return specService.findAll();
    }

    /**
     * 分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @GetMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Spec>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return specService.findByPager(page, pageSize);
    }

    /**
     * 按条件分页查询
     * @param spec      条件实体
     * @param page      当前页面
     * @param pageSize  每页大小
     * @return          响应数据
     */
    @PostMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Spec>> findPagerByParam(@RequestBody Spec spec, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return specService.findPagerByParam(spec, page, pageSize);
    }

    /**
     * 通过模板id查询规格
     * @param templateId        模板id
     * @return                  响应数据
     */
    @GetMapping("/findByTemplateId/{templateId}")
    public Result<List<Spec>> findByTemplateId(@PathVariable("templateId") Integer templateId) {
        return specService.findByTemplateId(templateId);
    }
}
