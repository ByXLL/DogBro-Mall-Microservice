package cn.byxll.user.feign;

import cn.byxll.user.pojo.User;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户service feign
 * @author By-Lin
 */
@FeignClient(value = "user")
@RequestMapping(value = "/user")
public interface UserFeign {
    /**
     * 根据id查询用户信息
     * @param id        用户id
     * @return          响应数据
     */
    @GetMapping("/load/{id}")
    Result<User> findById(@PathVariable("id") String id);
}
