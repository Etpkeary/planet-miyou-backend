package com.linglan.miyou.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @param <T> 任意数据
 * @description: 通用返回类
 * @author Linglan
 */
@Data
public class BaseResponse<T> implements Serializable {

    /**
     * @description: 状态码
     */
    @ApiModelProperty(value = "状态码", required = true)
    private int code;

    /**
     * @description: 数据
     */
    @ApiModelProperty(value = "数据", required = true)
    private T data;

    /**
     * @description: 消息
     */
    @ApiModelProperty(value = "消息", required = true)
    private String message;

    /**
     * @description: 描述
     */
    @ApiModelProperty(value = "描述", required = true)
    private String description;

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data, String message) {
        this(code, data, message, "");
    }

    public BaseResponse(int code, T data) {
        this(code, data, "", "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDescription());
    }
}
