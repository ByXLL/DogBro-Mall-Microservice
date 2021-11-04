package user;

import cn.byxll.user.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;

/**
 * jwt 测试类
 * @author By-Lin
 */
public class UserJwtTest {
    /****
     * 创建Jwt令牌
     */
    @Test
    public void testCreateJwt(){
        // 自定义载荷信息
        HashMap<String, Object> userInfo = new HashMap<>();
        userInfo.put("username","狗哥");
        userInfo.put("height", 178);

        JwtBuilder builder= Jwts.builder()
                .setId("666")                       // 设置唯一编号
                .setIssuer("狗哥")                  // 颁发者
                .setSubject("jwt测试数据")          // 设置主题  可以是JSON数据
                .addClaims(userInfo)               // 自定义载荷
                .setIssuedAt(new Date())          //设置签发日期
                .setExpiration(new Date(System.currentTimeMillis() + 360000))        //用于设置过期时间 ，参数为Date类型数据
                .signWith(SignatureAlgorithm.HS256,"DogBro");   //设置签名 使用HS256算法，并设置SecretKey 密钥
        //构建 并返回一个字符串
        System.out.println( builder.compact() );
    }

    /**
     * 解析Jwt令牌数据
     */
    @Test
    public void testParseJwt(){
        String compactJwt="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJpc3MiOiLni5flk6UiLCJzdWIiOiJqd3TmtYvor5XmlbDmja4iLCJ1c2VybmFtZSI6IueLl-WTpSIsImhlaWdodCI6MTc4LCJpYXQiOjE2MzYwMTg3MzIsImV4cCI6MTYzNjAxOTA5Mn0.rP29PKfiOn9LkSjlx_jjjiz3ZWspER69vDg1S3nv4YY";
        Claims claims = Jwts.parser()
                .setSigningKey("DogBro")            // 密钥
                .parseClaimsJws(compactJwt)         // 令牌 token
                .getBody();
        System.out.println(claims);
    }
}
