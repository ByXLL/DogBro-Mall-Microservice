package cn.byxll.user.controller;

import cn.byxll.user.dto.LoginFormDto;
import cn.byxll.user.pojo.User;
import cn.byxll.user.service.impl.UserServiceImpl;
import cn.byxll.user.vo.LoginResultVO;
import com.github.pagehelper.PageInfo;
import entity.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User 控制器类
 * PreAuthorize("hasAnyRole('vip')") 该注解用于权限校验 hasAnyRole 当拥有某一个角色是才可以调用该接口
 * @author By-Lin
 */

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<LoginResultVO> login(@RequestBody LoginFormDto loginFormDto, HttpServletResponse httpServletResponse) {
        return userService.login(loginFormDto,httpServletResponse);
    }

    /**
     * 新增User数据
     * @param user      User实体
     * @return              响应数据
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody User user){
        return userService.add(user);
    }

    /**
     * 根据ID删除User数据
     * @param id        主键
     * @return          响应数据
     */
    @PostMapping("/delete/{id}" )
    public Result<Boolean> delete(@PathVariable("id") String id){
        return userService.delete(id);
    }

    /**
     * 修改User数据
     * @param user      User实体
     * @return              响应数据
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody User user){
        return userService.update(user);
    }

    /**
     * 根据ID查询User数据
     * @param id        主键id
     * @return          响应数据
     */
    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable("id") String id){
        return userService.findById(id);
    }

    /**
     * 查询User全部数据
     * @return          响应数据
     */
    @GetMapping("/")
    @PreAuthorize("hasAnyRole('vip')")
    public Result<List<User>> findAll(){
        return userService.findAll();
    }

    /**
     * 多条件搜索品牌数据
     * @param user      User实体
     * @return              响应数据
     */
    @PostMapping("/search")
    public Result<List<User>> findList(@RequestBody User user){
        return userService.findList(user);
    }

    /**
     * User分页搜索实现
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @GetMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<User>> findByPager(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return userService.findByPager(page, pageSize);
    }


    /**
     * User分页条件搜索实现
     * @param user          User实体
     * @param page              当前页码
     * @param pageSize          每页大小
     * @return                  响应数据
     */
    @PostMapping(value = "/search/{page}/{pageSize}" )
    public Result<PageInfo<User>> findPagerByParam(@RequestBody User user, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize){
        return userService.findPagerByParam(user, page, pageSize);
    }

}
