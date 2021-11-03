package cn.byxll.user.service;

import cn.byxll.user.dto.LoginFormDto;
import cn.byxll.user.pojo.User;
import com.github.pagehelper.PageInfo;
import entity.Result;

import java.util.List;

/**
 * User业务层接口类
 * @author @By-Lin
 */
public interface UserService {

    /**
     * 登录
     * @param   loginFormDto      User实体
     * @return                    响应数据
     */
    Result<Boolean> login(LoginFormDto loginFormDto);


    /**
     * 新增User
     * @param   user      User实体
     * @return            响应数据
     */
    Result<Boolean> add(User user);

    /**
     * 通过组件删除User
     * @param id               主键id
     * @return                 响应数据
     */
    Result<Boolean> delete(String id);

    /**
     * 修改User数据
     * @param user      User实体
     * @return              响应数据
     */
    Result<Boolean> update(User user);

    /**
     * 根据ID查询User
     * @param id        主键id
     * @return          响应数据
     */
    Result<User> findById(String id);

    /**
     * 查询所有User
     * @return          响应数据
     */
    Result<List<User>> findAll();

    /**
     * User分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<User>> findByPager(Integer page, Integer pageSize);

    /**
     * User条件分页查询
     * @param user      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    Result<PageInfo<User>> findPagerByParam(User user, Integer page, Integer pageSize);
    

    /**
     * User多条件搜索方法
     * @param user      条件实体
     * @return              响应数据
     */
    Result<List<User>> findList(User user);
}
