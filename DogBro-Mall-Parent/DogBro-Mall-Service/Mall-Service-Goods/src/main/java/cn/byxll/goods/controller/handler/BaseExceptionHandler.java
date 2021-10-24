package cn.byxll.goods.controller.handler;

import entity.Result;
import entity.StatusCode;
import exception.OperationalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局控制器异常处理器
 * @author By-Lin
 */
@ResponseBody
@ControllerAdvice
public class BaseExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);
    /**
     * 404 请求处理器查找不到异常处理
     * 需要在配置文件中 设置
     *  throw-exception-if-no-handler-found：true
     *  web.resources.add-mappings: false
     * @param e     异常
     * @return      响应数据
     */
    @ExceptionHandler(value = { NoHandlerFoundException.class })
    public Result<Object> noFoundError(Exception e) {
//        e.printStackTrace();
        logger.error("接口404-------{}", e.getLocalizedMessage());
        return new Result<>(false, StatusCode.NOTFOUNDERROR, e.getLocalizedMessage());
    }


    /**
     * 参数校验失败处理器
     * @param e     异常
     * @return      响应数据
     */
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public Result<Object> argError(Exception e) {
        e.printStackTrace();
        return new Result<>(false, StatusCode.ARGERROR,"参数校验异常--"+e.getLocalizedMessage());
    }

    /**
     * 参数异常处理器
     * @param e     异常
     * @return      响应数据
     */
    @ExceptionHandler(value = { IllegalArgumentException.class })
    public Result<Object> illegalArgError(Exception e) {
        e.printStackTrace();
        return new Result<>(false, StatusCode.NOTFOUNDERROR, "参数异常---"+e.getLocalizedMessage());
    }

    /**
     * service 操作失败异常处理器
     * @param e     异常
     * @return      响应数据
     */
    @ExceptionHandler(value = { OperationalException.class })
    public Result<Object> operationalException(Exception e) {
        e.printStackTrace();
        return new Result<>(false, StatusCode.ERROR, e.getLocalizedMessage());
    }

    /**
     * 全局缺省异常处理器
     * @param e     异常
     * @return      响应数据
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> error(Exception e) {
        e.printStackTrace();
        return new Result<>(false, StatusCode.ERROR, "异常---"+e.getLocalizedMessage());
    }
}
