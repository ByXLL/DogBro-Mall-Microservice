package cn.byxll.user.controller;

import cn.byxll.user.pojo.UndoLog;
import cn.byxll.user.service.impl.UndoLogServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UndoLog 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/undoLog")
public class UndoLogController {

    private final UndoLogServiceImpl undoLogService;

    public UndoLogController(UndoLogServiceImpl undoLogService) {
        this.undoLogService = undoLogService;
    }

    /**
     * 新增UndoLog数据
     * @param undoLog      UndoLog实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody UndoLog undoLog){
        return undoLogService.add(undoLog);
    }

    /**
     * 根据ID删除UndoLog数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") Long id){
        return undoLogService.delete(id);
    }

    /**
     * 修改UndoLog数据
     * @param undoLog      UndoLog实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody UndoLog undoLog){
        return undoLogService.update(undoLog);
    }

    /**
     * 根据ID查询UndoLog数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<UndoLog> findById(@PathVariable("id") Long id){
        return undoLogService.findById(id);
    }

    /**
     * 查询UndoLog全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<UndoLog>> findAll(){
        return undoLogService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param undoLog      UndoLog实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<UndoLog>> findList(@RequestBody UndoLog undoLog){
        return undoLogService.findList(undoLog);
    }

    /**
     * UndoLog分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<UndoLog>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return undoLogService.findByPager(page, pageSize);
    }


    /**
     * UndoLog分页条件搜索实现
     * @param undoLog          UndoLog实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<UndoLog>> findPagerByParam(@RequestBody UndoLog undoLog, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return undoLogService.findPagerByParam(undoLog, page, pageSize);
    }

}
