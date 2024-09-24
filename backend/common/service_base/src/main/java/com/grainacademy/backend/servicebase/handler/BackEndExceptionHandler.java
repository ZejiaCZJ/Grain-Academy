package com.grainacademy.backend.servicebase.handler;

import com.grainacademy.backend.commonutils.Result;
import com.grainacademy.backend.servicebase.exceptions.BackEndException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class BackEndExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result failed(Exception e)
    {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.failed().message("Global exception executed");
    }


    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public Result failed(DuplicateKeyException e)
    {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.failed().message("Duplicate key found in the database");
    }


    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public Result failed(DataAccessException e)
    {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.failed().message("Failed to connect to MySQL database");
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    public Result failed(NoSuchElementException e)
    {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.failed().message("Failed to find the matched record in database");
    }

    @ExceptionHandler(BackEndException.class)
    @ResponseBody
    public Result failed(BackEndException e)
    {
        e.printStackTrace();
        return Result.failed().code(e.getCode()).message(e.getMsg());
    }
}
