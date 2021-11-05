package cn.byxll.user.vo;

import java.io.Serializable;

/**
 * 登录响应结果
 * @author By-Lin
 */
public class LoginResultVO implements Serializable {
    private String token;
    private UserVO userInfo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserVO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserVO userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "LoginResultVO{" +
                "token='" + token + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
