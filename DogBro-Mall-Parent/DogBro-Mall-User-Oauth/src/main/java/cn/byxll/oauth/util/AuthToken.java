package cn.byxll.oauth.util;

import java.io.Serializable;

/**
 * 用户令牌封装
 * @author By-Lin
 */
public class AuthToken implements Serializable{

    /** 令牌信息 */
    private String accessToken;

    /** 刷新token(refresh_token) */
    private String refreshToken;

    /** jwt短令牌 */
    private String jti;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }
}