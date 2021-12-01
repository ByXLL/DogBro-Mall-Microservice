package cn.byxll.oauth.util;

import com.alibaba.fastjson.JSON;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;

/**
 * 管理员token 用于服务间feign请求鉴权
 * @author By-Lin
 */
public class AdminToken {
    /**
     * 获取管理员令牌
     * @return      token
     */
    public static String getAdminToken() {
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
        payload.put("authorities",new String[]{"Admin","Oauth"});
        // 创建令牌
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(payload), new RsaSigner(privateKey));
        // 获取令牌数据
        return jwt.getEncoded();
    }
}
