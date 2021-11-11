package cn.byxll.oauth.controller;

import cn.byxll.oauth.util.AuthToken;
import cn.byxll.oauth.util.CookieUtil;
import cn.byxll.oauth.service.LoginService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * 用户登录请求
 * 在正常的认证中 我们需要在 body中将账号密码，和授权方式grant_type 还要在请求头中将客户端id和客户端密码回传
 * 但是真实环境中，是不可能将客户端id和密码交给用户的
 * 所有我们将这些默认携带的参数在请求中自动加上，让用户只用提交账号和密码即可
 * @author By-Lin
 */
@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    /** 授权模式 密码模式 */
    private static final String GRANT_TYPE = "password";

    private final LoginService loginService;

    public UserLoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    /**
     * 密码模式  认证.
     * @param username      用户名
     * @param password      密码
     * @return              响应结果
     */
    @RequestMapping("/login")
    public Result<AuthToken> login(String username, String password) {
        return loginService.login(username, password, clientId, clientSecret, GRANT_TYPE);
    }
}
