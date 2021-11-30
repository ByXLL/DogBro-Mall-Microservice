package cn.byxll.order.controller;

import cn.byxll.order.pojo.TaskHis;
import cn.byxll.order.service.impl.TaskHisServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TaskHis 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/taskHis")
public class TaskHisController {

    private final TaskHisServiceImpl taskHisService;

    public TaskHisController(TaskHisServiceImpl taskHisService) {
        this.taskHisService = taskHisService;
    }

    /**
     * 新增TaskHis数据
     * @param taskHis      TaskHis实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody TaskHis taskHis){
        return taskHisService.add(taskHis);
    }

    /**
     * 根据ID删除TaskHis数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") Long id){
        return taskHisService.delete(id);
    }

    /**
     * 修改TaskHis数据
     * @param taskHis      TaskHis实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody TaskHis taskHis){
        return taskHisService.update(taskHis);
    }

    /**
     * 根据ID查询TaskHis数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<TaskHis> findById(@PathVariable("id") Long id){
        return taskHisService.findById(id);
    }

    /**
     * 查询TaskHis全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<TaskHis>> findAll(){
        return taskHisService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param taskHis      TaskHis实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<TaskHis>> findList(@RequestBody TaskHis taskHis){
        return taskHisService.findList(taskHis);
    }

    /**
     * TaskHis分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<TaskHis>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return taskHisService.findByPager(page, pageSize);
    }


    /**
     * TaskHis分页条件搜索实现
     * @param taskHis          TaskHis实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<TaskHis>> findPagerByParam(@RequestBody TaskHis taskHis, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return taskHisService.findPagerByParam(taskHis, page, pageSize);
    }

}
