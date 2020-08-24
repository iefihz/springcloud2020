package com.iefihz.springcloud.exception;

import lombok.Getter;

/**
 * ApiException异常类
 *
 * @author He Zhifei
 * @date 2020/6/6 13:50
 */
@Getter
public class ApiException extends RuntimeException {

    private int code;

    private String message;

    public ApiException() {
        this(1001, "接口错误");
    }

    public ApiException(String message) {
        this(1001, message);
    }

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
