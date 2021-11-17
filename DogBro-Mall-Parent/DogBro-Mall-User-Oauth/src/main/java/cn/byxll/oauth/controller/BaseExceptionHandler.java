package cn.byxll.oauth.controller;

import entity.Result;
import entity.StatusCode;
import exception.UserNoFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 * @author By-Lin
 */
//@ControllerAdvice
public class BaseExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Object> error(Exception e) {
        e.printStackTrace();
        return new Result<>(false, StatusCode.ERROR,e.getLocalizedMessage());
    }

    @ResponseBody
    @ExceptionHandler(UserNoFoundException.class)
    public Result<Object> userNoFound(Exception e) {
        e.printStackTrace();
        return new Result<>(false, StatusCode.LOGINERROR,"用户登录异常，请检查用户名和密码!");
    }

}
