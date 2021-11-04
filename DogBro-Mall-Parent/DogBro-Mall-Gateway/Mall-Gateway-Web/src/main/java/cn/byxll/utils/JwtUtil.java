package cn.byxll.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * jwt 工具类
 * @author By-Lin
 */
public class JwtUtil {
    /**
     * 有效期
     * 60 * 60 *1000  一个小时
     */
    public static final Long JWT_TTL = 3600000L;

    // Jwt令牌信息
    public static final String JWT_KEY = "Aa65536";

    /**
     * 创建jwt
     * @param id            唯一编号
     * @param subject       主题信息
     * @param ttlMillis     过期时间 null 为一小时
     * @return              jwt 字符串
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        // 指定算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 当前系统时间
        long nowMillis = System.currentTimeMillis();
        // 令牌签发时间
        Date now = new Date(nowMillis);

        // 如果令牌有效期为null，则默认设置有效期1小时
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }

        // 令牌过期时间设置
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        // 生成秘钥
        SecretKey secretKey = generalKey();

        // 封装Jwt令牌信息
        JwtBuilder builder = Jwts.builder()
                .setId(id)                    //唯一的ID
                .setSubject(subject)          // 主题  可以是JSON数据
                .setIssuer("By-Lin")          // 签发者
                .setIssuedAt(now)             // 签发时间
                .signWith(signatureAlgorithm, secretKey) // 签名算法以及密匙
                .setExpiration(expDate);      // 设置过期时间
        return builder.compact();
    }

    /**
     * 生成加密 secretKey
     * @return  盐
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(JwtUtil.JWT_KEY.getBytes());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }


    /**
     * 解析令牌数据
     * @param jwt               token字符串
     * @return                  载体信息
     * @throws Exception        异常处理
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
