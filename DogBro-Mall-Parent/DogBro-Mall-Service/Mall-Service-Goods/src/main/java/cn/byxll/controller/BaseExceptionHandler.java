package cn.byxll.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 * @author By-Lin
 */
@ControllerAdvice
public class BaseExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Object> error(Exception e) {
        e.printStackTrace();
        return new Result<>(false, StatusCode.ERROR,e.getLocalizedMessage());
    }
}
