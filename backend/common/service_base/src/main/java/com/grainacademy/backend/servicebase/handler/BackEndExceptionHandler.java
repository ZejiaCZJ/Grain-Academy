package com.grainacademy.backend.servicebase.handler;

import com.grainacademy.backend.commonutils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BackEndExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result failed(Exception e)
    {
        e.printStackTrace();
        return Result.failed().message("Global exception executed");
    }

}
