package cn.byxll.user.service.impl;

import cn.byxll.user.dao.UserMapper;
import cn.byxll.user.dto.LoginFormDto;
import cn.byxll.user.pojo.User;
import cn.byxll.user.service.UserService;
import cn.byxll.user.vo.LoginResultVO;
import cn.byxll.user.vo.UserVO;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import utils.BCrypt;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import utils.JwtUtil;
import utils.PasswordUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * User业务层接口实现类
 * @author By-Lin
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 登录
     * @param loginFormDto  User实体
     * @param   httpServletResponse     响应体
     * @return              响应数据
     */
    @Override
    public Result<LoginResultVO> login(LoginFormDto loginFormDto, HttpServletResponse httpServletResponse) {
        if(loginFormDto == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        User user = userMapper.selectByPrimaryKey(loginFormDto.getUserName());
        if(user == null) { return new Result<>(false, StatusCode.LOGINERROR, "登录失败，请检查用户名和密码"); }
        if(PasswordUtil.verifyPassword(loginFormDto.getPassword(),user.getPassword())) {
            // 创建用户令牌
            Map<String,Object> info = new HashMap<>(16);
            info.put("role","USER");
            info.put("success","SUCCESS");
            info.put("username",loginFormDto.getUserName());
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(info), null);
            // 将令牌存入Cookie
            Cookie cookie = new Cookie("Authorization", token);
            cookie.setDomain("localhost");
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
            // 封装用户信息回传
            LoginResultVO loginResultVO = new LoginResultVO();
            loginResultVO.setToken(token);
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user,userVO);
            loginResultVO.setUserInfo(userVO);
            return new Result<>(true, StatusCode.OK, "登录成功",loginResultVO);
        }
        return new Result<>(false, StatusCode.LOGINERROR, "登录失败，请检查用户名和密码",null);
    }

    /**
     * 新增User
     * @param user      User实体
     * @return               响应数据
     */
    @Override
    public Result<Boolean> add(User user){
        if(user == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        user.setPassword(PasswordUtil.getMd5Password(user.getPassword()));
        int i = userMapper.insert(user);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 删除
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<Boolean> delete(String id){
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = userMapper.deleteByPrimaryKey(id);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 修改User
     * @param user      User实体
     * @return              响应数据
     */
    @Override
    public Result<Boolean> update(User user){
        if(user == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        int i = userMapper.updateByPrimaryKey(user);
        if(i>0) { return new Result<>(true, StatusCode.OK, "操作成功"); }
        return new Result<>(false, StatusCode.ERROR, "操作失败");
    }

    /**
     * 根据ID查询User
     * @param id        主键id
     * @return          响应数据
     */
    @Override
    public Result<User> findById(String id) {
        if(StringUtils.isEmpty(id)) { return new Result<>(false, StatusCode.ARGERROR, "参数异常"); }
        User user = userMapper.selectByPrimaryKey(id);
        if(user != null) { return new Result<>(true, StatusCode.OK, "查询成功", user); }
        return new Result<>(false, StatusCode.ERROR, "查询失败",null);
    }

    /**
     * 查询所有User
     * @return      响应数据
     */
    @Override
    public Result<List<User>> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", userMapper.selectAll());
    }

    /**
     * User分页查询
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<User>> findByPager(Integer page, Integer pageSize){
        if(page == null || pageSize == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常", null); }
        //静态分页
        PageHelper.startPage(page,pageSize);
        //分页查询
        return new Result<>(true, StatusCode.OK, "查询成功", new PageInfo<>(userMapper.selectAll()));
    }


    /**
     * User条件分页查询
     * @param user      条件实体
     * @param page          当前页码
     * @param pageSize      每页大小
     * @return              响应数据
     */
    @Override
    public Result<PageInfo<User>> findPagerByParam(User user, Integer page, Integer pageSize){
        //分页
        PageHelper.startPage(page,pageSize);
        //搜索条件构建
        Example example = createExample(user);
        //执行搜索
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<User>(userMapper.selectByExample(example)));
    }

    /**
     * User多条件搜索方法
     * @param user      条件实体
     * @return              响应数据
     */
    @Override
    public Result<List<User>> findList(User user){
        if(user == null) { return new Result<>(false, StatusCode.ARGERROR, "参数异常",null); }
        //构建查询条件
        Example example = createExample(user);
        //根据构建的条件查询数据
        return new Result<>(true, StatusCode.OK, "操作成功", new PageInfo<User>(userMapper.selectByExample(example)));
    }


    /**
     * User构建查询对象
     * @param user      User实体
     * @return              查询对象
     */
    private Example createExample(User user){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(user!=null){
            // 用户名
            if(!StringUtils.isEmpty(user.getUsername())){
                criteria.andLike("username","%"+user.getUsername()+"%");
            }
            // 密码，加密存储
            if(!StringUtils.isEmpty(user.getPassword())){
                criteria.andEqualTo("password",user.getPassword());
            }
            // 注册手机号
            if(!StringUtils.isEmpty(user.getPhone())){
                criteria.andEqualTo("phone",user.getPhone());
            }
            // 注册邮箱
            if(!StringUtils.isEmpty(user.getEmail())){
                criteria.andEqualTo("email",user.getEmail());
            }
            // 创建时间
            if(!StringUtils.isEmpty(user.getCreated())){
                criteria.andEqualTo("created",user.getCreated());
            }
            // 修改时间
            if(!StringUtils.isEmpty(user.getUpdated())){
                criteria.andEqualTo("updated",user.getUpdated());
            }
            // 会员来源：1:PC，2：H5，3：Android，4：IOS
            if(!StringUtils.isEmpty(user.getSourceType())){
                criteria.andEqualTo("sourceType",user.getSourceType());
            }
            // 昵称
            if(!StringUtils.isEmpty(user.getNickName())){
                criteria.andEqualTo("nickName",user.getNickName());
            }
            // 真实姓名
            if(!StringUtils.isEmpty(user.getName())){
                criteria.andLike("name","%"+user.getName()+"%");
            }
            // 使用状态（1正常 0非正常）
            if(!StringUtils.isEmpty(user.getStatus())){
                criteria.andEqualTo("status",user.getStatus());
            }
            // 头像地址
            if(!StringUtils.isEmpty(user.getHeadPic())){
                criteria.andEqualTo("headPic",user.getHeadPic());
            }
            // QQ号码
            if(!StringUtils.isEmpty(user.getQq())){
                criteria.andEqualTo("qq",user.getQq());
            }
            // 手机是否验证 （0否  1是）
            if(!StringUtils.isEmpty(user.getIsMobileCheck())){
                criteria.andEqualTo("isMobileCheck",user.getIsMobileCheck());
            }
            // 邮箱是否检测（0否  1是）
            if(!StringUtils.isEmpty(user.getIsEmailCheck())){
                criteria.andEqualTo("isEmailCheck",user.getIsEmailCheck());
            }
            // 性别，1男，0女
            if(!StringUtils.isEmpty(user.getSex())){
                criteria.andEqualTo("sex",user.getSex());
            }
            // 会员等级
            if(!StringUtils.isEmpty(user.getUserLevel())){
                criteria.andEqualTo("userLevel",user.getUserLevel());
            }
            // 积分
            if(!StringUtils.isEmpty(user.getPoints())){
                criteria.andEqualTo("points",user.getPoints());
            }
            // 经验值
            if(!StringUtils.isEmpty(user.getExperienceValue())){
                criteria.andEqualTo("experienceValue",user.getExperienceValue());
            }
            // 出生年月日
            if(!StringUtils.isEmpty(user.getBirthday())){
                criteria.andEqualTo("birthday",user.getBirthday());
            }
            // 最后登录时间
            if(!StringUtils.isEmpty(user.getLastLoginTime())){
                criteria.andEqualTo("lastLoginTime",user.getLastLoginTime());
            }
        }
        return example;
    }

}
