package cn.byxll.token;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;
import java.util.HashMap;

/**
 * 创建jwt demo
 * 令牌的创建和解析
 * @author By-Lin
 */
public class CreateJwtTestDemo {

    /**
     * 创建令牌
     * 创建令牌，需要用私钥加密
     * 1、需要先获取证书 .jks文件 然后读取证书数据
     * 2、获取私钥
     * 3、使用私钥加盐【RSA算法】
     */
    @Test
    public void testCreateToken() {
        // 加载类路径下的文件
        ClassPathResource resource = new ClassPathResource("dogbro.jks");
        // 读取证书数据 文件名 加载文件需要的密码
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource,"Aa123456".toCharArray());
        // 获取证书中一对密钥
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("DogBro", "Aa123456".toCharArray());
        // 获取私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 创建一些载荷
        HashMap<String,Object> payload = new HashMap<>();
        payload.put("userName","狗哥");
        payload.put("height",178);
        payload.put("role","Admin,User");
        // 创建令牌
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(payload), new RsaSigner(privateKey));
        // 获取令牌数据
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }


    /**
     * 解析令牌
     */
    @Test
    public void testParseToken() {
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiQWRtaW4sVXNlciIsInVzZXJOYW1lIjoi54uX5ZOlIiwiaGVpZ2h0IjoxNzh9.NburGBYbL0TA2fX7Wz4yEMGPvs6iKqpgdP_585KOBnWQJ42YZI1WSX5TyHWAKX00bsHTIgZkjkpYN0jl6aj4sOCC8rbDSXpwLjn3KHlDxRsp5iWHa2ZKoPF5XQU1gdqm2y1sjrg4NuIaLTyo3ugrJw2Ouh8eUMY9JhgdVqJ3MUr5FAFUeHquB4pwOVO7e8vob4E3Lbi8Hc0upXb3f79lPQzRLykNruAzCzSlr8Tb-nGlS2SqPhQ9khXnJjc4dfTjKuKqtnf-YRKqie0t1TOTYrZaEY4FMs7dXpxa0VRtY1FDd2qM3mfZ2kzte840TCOIbcG8EuDAbZz_L8WeCVZLcA";
        String publickey = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1CmzI6X6fJVOdXGCa7Ds\n" +
                "wOId9V5+5s1Q07yZ9E+Knhg+7q23pONTqP99FwVOaq22/PDqlWb2kbM7wVy1s28h\n" +
                "0ZMMR8Tay7XnbUS3s7Y3HdK6J3Rr5YEkUGENe+10ulbftTWn7TULVSIpxKlducGh\n" +
                "NIThtFHy2wzlXA0EKeAG1NXoX8bwkO8mIoCBrmFHUEvnYVMsprTQu0C/Uprs4Did\n" +
                "WwEFxTl4uptBpeMzLeydjptMZsZ8EvIFj56sLEAsqvYeLtCUKCK5xAJCVQsYchff\n" +
                "sk/cP/D/lRaHG9U2hXPnN0Z2bzlORSum98OocXkZtdLvjuOIe5lThTOdY6A4XsYZ\n" +
                "UwIDAQAB\n" +
                "-----END PUBLIC KEY-----";
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));
        String jwtClaims = jwt.getClaims();  // 就是之前jwt 里携带的载荷
        System.out.println(jwtClaims);
    }

    /**
     * 验证 在请求中去声明 authorization 携带的客户端id和密码是用base64加密的
     * authorization = Basic RG9nQnJvOkFhMTIzNDU2
     */
    @Test
    public void testDecodeBase64() {
        String str = "RG9nQnJvOkFhMTIzNDU2";
        byte[] decode = Base64.getDecoder().decode(str);
        String decodeStr = new String(decode, StandardCharsets.UTF_8);
        System.out.println(decodeStr); // DogBro:Aa123456 正是客户端账号和密码
    }
}
