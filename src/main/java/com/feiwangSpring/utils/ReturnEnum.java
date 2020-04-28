package com.feiwangSpring.utils;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/27 10:06
 */
public enum ReturnEnum {
    success("200","",""),
    error("500","error","后台发生未知错误");

    //自定义异常码
    private String code;
    //国际化文件中的key
    private String type;
    //异常信息说明
    private String message;

    ReturnEnum(String code, String type, String message){
        this.code = code;
        this.type = type;
        this.message=message;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ReturnEnum{" +
                "code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
