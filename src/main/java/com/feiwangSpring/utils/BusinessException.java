package com.feiwangSpring.utils;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/27 9:59
 */
public class BusinessException extends Exception {
    private ReturnEnum returnEnum;
    private Throwable cause;

    public BusinessException(ReturnEnum returnEnum) {
        super(returnEnum.getMessage());
        this.returnEnum = returnEnum;
    }

    public BusinessException(ReturnEnum returnEnum, Throwable cause) {
        super(returnEnum.getMessage(), cause);
        this.returnEnum = returnEnum;
        this.cause = cause;
    }
    public BusinessException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    public ReturnEnum getReturnEnum() {
        return returnEnum;
    }
}

