package cn.byxll.user.controller;

import cn.byxll.user.pojo.Address;
import cn.byxll.user.pojo.Areas;
import cn.byxll.user.service.AreasService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 行政区域县区信息 controller 类
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/areas")
public class AreasController {

    private final AreasService areasService;

    public AreasController(AreasService areasService) {
        this.areasService = areasService;
    }

    /**
     * 添加区域县区信息
     *
     * @param areas 区县信息实体
     * @return 响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Areas areas) {
        return areasService.add(areas);
    }

    /**
     * 删除Areas
     *
     * @param id 主键id
     * @return 响应数据
     */
    @PostMapping(value = "/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") String id) {
        return areasService.delete(id);
    }

    /**
     * 修改Areas
     *
     * @param areas 区县信息实体
     * @return 响应数据
     */
    @PostMapping(value = "/update")
    public Result<Boolean> update(@RequestBody Areas areas) {
        return areasService.update(areas);
    }

    /**
     * 根据ID查询Areas
     *
     * @param id 主键
     * @return 响应数据
     */
    @GetMapping("/{id}")
    public Result<Areas> findById(@PathVariable("id") String id) {
        return areasService.findById(id);
    }

    /**
     * 查询Areas全部数据
     *
     * @return 响应数据
     */
    @GetMapping("/")
    public Result<List<Areas>> findAll() {
        return areasService.findAll();
    }

    /**
     * 按条件查询
     *
     * @param areas 条件实体
     * @return 响应数据
     */
    @PostMapping("/search")
    public Result<List<Areas>> findList(Areas areas) {
        return areasService.findList(areas);
    }

    /**
     * 分页查询
     *
     * @param page     当前页码
     * @param pageSize 每页大小
     * @return 响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}")
    public Result<PageInfo<Areas>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return areasService.findByPager(page, pageSize);
    }

    /**
     * 按条件分页查询
     *
     * @param areas    条件实体
     * @param page     当前页码
     * @param pageSize 每页大小
     * @return 响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}")
    public Result<PageInfo<Areas>> findByPagerParam(@RequestBody Areas areas, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return areasService.findByPagerParam(areas, page, pageSize);
    }
}
