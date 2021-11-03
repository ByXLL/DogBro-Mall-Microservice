package cn.byxll.user.dto;

import java.io.Serializable;

/**
 * 登录表单 dto
 * @author By-Lin
 */
public class LoginFormDto implements Serializable {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginFormDto{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
