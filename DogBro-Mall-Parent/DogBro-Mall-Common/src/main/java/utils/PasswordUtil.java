package utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;

/**
 * 密码工具类
 * @author By-Lin
 */
public class PasswordUtil {
    /** 加盐 */
    private static final String SALT = "NjU1MzY=";

    /**
     * 获取md5加密后的密码
     * @param password      原密码
     * @return              md5加密后的密码
     */
    public static String getMd5Password(String password) {
        if (StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("MD5加密失败,密码为空");
        }
//        System.out.println(password+SALT);
        return DigestUtils.md5DigestAsHex((password+SALT).getBytes());
    }

    /**
     * 校验密码 是否通过
     * @param password              密码
     * @param encryptedPassword     加密后的密码
     * @return                      校验结果
     */
    public static boolean verifyPassword(String password, String encryptedPassword) {
        if(StringUtils.isEmpty(password) || StringUtils.isEmpty(encryptedPassword)) {
            return false;
        }
        return StringUtils.equals(getMd5Password(password),encryptedPassword);
    }
}
