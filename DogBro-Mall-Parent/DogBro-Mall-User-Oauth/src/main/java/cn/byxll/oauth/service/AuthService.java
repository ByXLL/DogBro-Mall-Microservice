package cn.byxll.oauth.service;

import cn.byxll.oauth.util.AuthToken;

/**
 * 权限校验 service 接口类
 * @author By-Lin
 */
public interface AuthService {

    /***
     * 授权认证方法
     */
    AuthToken login(String username, String password, String clientId, String clientSecret);
}
