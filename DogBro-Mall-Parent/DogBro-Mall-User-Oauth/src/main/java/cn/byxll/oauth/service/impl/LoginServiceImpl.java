package cn.byxll.oauth.service.impl;

import cn.byxll.oauth.service.LoginService;
import cn.byxll.oauth.util.AuthToken;
import cn.byxll.oauth.util.CookieUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;

/**
 * 用户登录service 接口实现类
 * @author By-Lin
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Value("${auth.cookieDomain}")
    private String cookieDomain;

    /** Cookie生命周期 */
    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;

    private final RestTemplate restTemplate;

    private final LoadBalancerClient loadBalancerClient;

    public LoginServiceImpl(RestTemplate restTemplate, LoadBalancerClient loadBalancerClient) {
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
    }

    /**
     * 模拟用户的行为 发送请求 申请令牌 返回
     * @param username          用户名
     * @param password          密码
     * @param clientId          客户端id
     * @param clientSecret      客户端密钥
     * @param grandType         认证方式
     * @return                  token
     */
    @Override
    public Result<AuthToken> login(String username, String password, String clientId, String clientSecret, String grandType) {

        /*
         *  1.定义url (申请令牌的url)
         *  参数 : 微服务的名称spring.application指定的名称
         *  loadBalancerClient 获取指定服务的注册数据
         */

        ServiceInstance choose = loadBalancerClient.choose("user-auth");
        String url = choose.getUri().toString()+"/oauth/token";

        // 2.定义请求头信息 (客户端id和客户端密钥) base64加密
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization","Basic "+Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()));

        // 3. 定义请求体  有授权模式 用户的名称 和密码
        MultiValueMap<String,String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type",grandType);
        formData.add("username",username);
        formData.add("password",password);

        // 4.模拟浏览器 发送POST 请求 携带 头 和请求体 到认证服务器
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

        /*
         * 参数1  指定要发送的请求的url
         * 参数2  指定要发送的请求的方法 post
         * 参数3  指定请求实体(包含头和请求体数据)
         * 参数4  返回数据需要转换的类型
         */
        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
        int statusCode = responseEntity.getStatusCode().value();
        if(!"200".equals(String.valueOf(statusCode)) || responseEntity.getBody() == null) {
            return new Result<>(false,StatusCode.LOGINERROR,"登录失败，请检查用户名和密码",null);
        }

        //5.接收到返回的响应(就是:令牌的信息)
        Map body = responseEntity.getBody();

        // 封装AuthToken.
        AuthToken authToken = new AuthToken();
        // 访问令牌(jwt)
        authToken.setJti((String) body.get("jti"));
        // 刷新令牌(jwt)
        authToken.setAccessToken((String) body.get("access_token"));
        // jti，作为用户的身份标识
        authToken.setRefreshToken((String) body.get("refresh_token"));

        // 设置到cookie中
        saveCookie(authToken.getAccessToken());

        //6.返回
        return new Result<>(true, StatusCode.OK,"登录成功",authToken);
    }

    /**
     * 设置cookie
     * @param token     token
     */
    private void saveCookie(String token){
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        if (response != null) {
            CookieUtil.addCookie(response,cookieDomain,"/","Authorization",token,cookieMaxAge,false);
        }
    }

    public static void main(String[] args) {
        byte[] decode = Base64.getDecoder().decode(new String("Y2hhbmdnb3UxOmNoYW5nZ291Mg==").getBytes());
        System.out.println(new String(decode));
    }
}
