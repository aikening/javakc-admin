package com.javakc.servicebase.handler;


import com.javakc.commonutils.api.APICODE;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public APICODE erroHandler(Exception e){
        e.printStackTrace();
        return APICODE.ERROR().message("Exception:服务器异常！");
    }

    @ExceptionHandler(HctfException.class)
    @ResponseBody
    public APICODE erroHandler(HctfException e){
        e.printStackTrace();
        return APICODE.ERROR().message(e.getMsg()).code(e.getCode());
    }

}
