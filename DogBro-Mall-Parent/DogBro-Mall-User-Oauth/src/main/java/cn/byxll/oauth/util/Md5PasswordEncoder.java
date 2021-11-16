package cn.byxll.oauth.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;
import utils.PasswordUtil;

/**
 * SpringSecurity 自定义的MD5密码加密方式
 * 调用我们封装得全局 md5加密工具类
 * @author By-Lin
 */
public class Md5PasswordEncoder implements PasswordEncoder {
    /**
     * 获取md5加密后的密码
     * @param rawPassword      原密码
     * @return                 md5加密后的密码
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return PasswordUtil.getMd5Password((String) rawPassword);
    }

    /**
     * 校验密码 是否通过
     * @param rawPassword              密码
     * @param encodedPassword          加密后的密码
     * @return                         校验结果
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return PasswordUtil.verifyPassword((String) rawPassword,encodedPassword);
    }
}
