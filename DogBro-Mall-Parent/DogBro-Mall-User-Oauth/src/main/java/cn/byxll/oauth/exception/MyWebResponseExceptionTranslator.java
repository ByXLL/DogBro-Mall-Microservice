package cn.byxll.oauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * 资源访问异常转义器
 * 用于将oauth默认的异常返回的json数据转换成自定义的
 *
 * 对于登录认证异常处理配置，然后是处理类，
 * 通过WebResponseExceptionTranslator转为自定义的OAuth2Exception，
 * 然后使用jackson定制化类的json序列化，达到自定义异常信息的目的。
 *
 * OAuth2Exception 本身是继承RuntimeException，而且被OAuth2ExceptionJackson1Serializer序列化，
 * 所以我们要做的就是自定义一个OAuth2Exception 子类 和这个子类的序列化
 * @author By-Lin
 */
public class MyWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception exception) throws Exception {
        if (exception instanceof OAuth2Exception) {
            OAuth2Exception oAuth2Exception = (OAuth2Exception) exception;
            return ResponseEntity
                    .status(oAuth2Exception.getHttpErrorCode())
                    .body(new CustomOauthException(oAuth2Exception.getMessage()));
        }else if(exception instanceof AuthenticationException){
            AuthenticationException authenticationException = (AuthenticationException) exception;
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new CustomOauthException(authenticationException.getMessage()));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CustomOauthException(exception.getMessage()));
    }
}
