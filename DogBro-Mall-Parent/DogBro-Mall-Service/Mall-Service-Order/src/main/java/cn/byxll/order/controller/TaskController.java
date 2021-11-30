package cn.byxll.order.controller;

import cn.byxll.order.pojo.Task;
import cn.byxll.order.service.impl.TaskServiceImpl;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Task 控制器类
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    /**
     * 新增Task数据
     * @param task      Task实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Task task){
        return taskService.add(task);
    }

    /**
     * 根据ID删除Task数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") Long id){
        return taskService.delete(id);
    }

    /**
     * 修改Task数据
     * @param task      Task实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Task task){
        return taskService.update(task);
    }

    /**
     * 根据ID查询Task数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<Task> findById(@PathVariable("id") Long id){
        return taskService.findById(id);
    }

    /**
     * 查询Task全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    public Result<List<Task>> findAll(){
        return taskService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param task      Task实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<Task>> findList(@RequestBody Task task){
        return taskService.findList(task);
    }

    /**
     * Task分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<Task>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return taskService.findByPager(page, pageSize);
    }


    /**
     * Task分页条件搜索实现
     * @param task          Task实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<Task>> findPagerByParam(@RequestBody Task task, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return taskService.findPagerByParam(task, page, pageSize);
    }

}
