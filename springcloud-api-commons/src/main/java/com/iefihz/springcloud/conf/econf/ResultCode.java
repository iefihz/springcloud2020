package com.iefihz.springcloud.conf.econf;

import lombok.Getter;

/**
 * 异常码
 *
 * @author He Zhifei
 * @date 2020/6/6 13:45
 */
@Getter
public enum ResultCode {

    SUCCESS(200, "操作成功"),

    FAILED(1001, "响应失败"),

    VALIDATE_FAILED(1002, "参数校验失败"),

    ERROR(5000, "未知错误");

    private int code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}