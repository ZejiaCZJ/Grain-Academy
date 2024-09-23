package com.grainacademy.backend.commonutils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {
    @Schema(description = "request succeed or failed")
    private Boolean success;

    @Schema(description = "return code")
    private Integer code;

    @Schema(description = "return message")
    private String message;

    @Schema(description = "return data")
    private Map<String, Object> data = new HashMap<String, Object>();

    private Result(){}

    public static Result succeed(){
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("Succeed");
        return r;
    }

    public static Result failed(){
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("Failed");
        return r;
    }

    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value)
    {
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map)
    {
        this.setData(map);
        return this;
    }
}
