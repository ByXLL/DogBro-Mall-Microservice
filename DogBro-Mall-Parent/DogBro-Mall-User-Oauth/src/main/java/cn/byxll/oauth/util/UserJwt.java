package cn.byxll.oauth.util;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 扩展的用户jwt 对象
 * 用于对 security user类的一个信息扩展 用于token载荷
 * @author By-Lin
 */
public class UserJwt extends User {
    /** 用户ID */
    private String id;

    /** 用户名字 */
    private String name;

    /** 公司信息 */
    private String company;

    public UserJwt(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
