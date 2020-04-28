package com.feiwangSpring.utils;

import java.io.Serializable;


public class JsonResult<T> implements Serializable {
    private String code;
    private String message;
    private T data;

    public JsonResult(String code,String message,T data){
        this.code=code;
        this.message = message;
        this.data = data;
    }
    public JsonResult(ReturnEnum returnEnum, T data){
        this.code = returnEnum.getCode();
        this.message = returnEnum.getMessage();
        this.data = data;
    }
    public JsonResult(ReturnEnum returnEnum){
        this.code = returnEnum.getCode();
        this.message = returnEnum.getMessage();
    }

    public static JsonResult success(){
        return new JsonResult(ReturnEnum.success.getCode(),ReturnEnum.success.getMessage(),null);
    }
    public static <T> JsonResult success(T data){
        return new JsonResult(ReturnEnum.success.getCode(),ReturnEnum.success.getMessage(),data);
    }


    public static JsonResult error(){
        return new JsonResult(ReturnEnum.error.getCode(),ReturnEnum.error.getMessage(),null);
    }
    public static <T> JsonResult error(ReturnEnum returnEnum){
        return new JsonResult(returnEnum.getCode(),returnEnum.getMessage(),null);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}

