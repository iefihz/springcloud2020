package com.iefihz.springcloud.conf.econf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口返回值包装类
 * @param <T>  返回数据具体类型
 *
 * @author He Zhifei
 * @date 2020/6/6 13:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    /**
     * 状态码，比如1000代表响应成功
     */
    private int code;

    /**
     * 响应信息，用来说明响应情况
     */
    private String message;

    /**
     * 响应的具体数据
     */
    private T data;

    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

}
