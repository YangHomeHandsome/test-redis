package com.itmayiedu.Util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by YJJ on 2019/9/23.
 */
//配置切面类注解
@ControllerAdvice
//全局异常处理类
public class GlobalExceptionHandler {
    //异常处理注解
    @ExceptionHandler(RuntimeException.class)
    //这里是跳转页面，如果是JSON格式可以返回Object类型并且加@ResponseBody注解
    public String handleException(Exception e){
        e.printStackTrace();
        return "500.html";
    }
}
