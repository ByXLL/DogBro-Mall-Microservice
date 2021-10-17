package cn.byxll.controller;

import cn.byxll.goods.pojo.Para;
import cn.byxll.service.impl.ParaServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

/**
 * 参数 控制器
 * @author By-Lin
 */
@CrossOrigin
@RestController
@RequestMapping("/para")
public class ParaController {
    private final ParaServiceImpl paraService;

    public ParaController(ParaServiceImpl paraService) {
        this.paraService = paraService;
    }

    /**
     * 添加参数
     * @param para      参数实体
     * @return          响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Para para) {
        return paraService.add(para);
    }

    /**
     * 删除参数
     * @param id        参数id
     * @return          响应数据
     */
    @PostMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") Integer id) {
        return paraService.delete(id);
    }

    /**
     * 修改参数
     * @param para      参数实体
     * @return          响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Para para) {
        return paraService.update(para);
    }

    /**
     * 通过id查询参数
     * @param id        参数id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<Para> findById(@PathVariable("id") Integer id) {
        return paraService.findById(id);
    }

    /**
     * 分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @GetMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Para>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return paraService.findByPager(page, pageSize);
    }

    /**
     * 按条件分页查询
     * @param para      参数实体
     * @param page      当前页码
     * @param pageSize  每页大小
     * @return          响应数据
     */
    @PostMapping("/search/{page}/{pageSize}")
    public Result<PageInfo<Para>> findPagerByParam(@RequestBody Para para, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return paraService.findPagerByParam(para, page, pageSize);
    }
}
