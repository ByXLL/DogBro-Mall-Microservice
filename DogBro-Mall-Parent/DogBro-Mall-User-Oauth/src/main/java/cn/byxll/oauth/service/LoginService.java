package cn.byxll.oauth.service;

import cn.byxll.oauth.util.AuthToken;
import entity.Result;

/**
 * 用户登录service 接口
 * @author By-Lin
 */
public interface LoginService {
    /**
     * 模拟用户的行为 发送请求 申请令牌 返回
     * @param username          用户名
     * @param password          密码
     * @param clientId          客户端id
     * @param clientSecret      客户端密钥
     * @param grandType         认证方式
     * @return                  响应信息
     */
    Result<AuthToken> login(String username, String password, String clientId, String clientSecret, String grandType);
}
