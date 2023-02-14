package com.linglan.miyou.common;

/**
 * @description: 自定义错误码
 * @author Linglan
 */
public enum ErrorCode {

    SUCCESS(0, "ok", ""),
    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    ACCOUNT_OR_PASSWORD_ERROR(40002,"账号或密码错误",""),
    NOT_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", ""),
    SYSTEM_ERROR(50000, "系统内部异常", "");

    /**
     * @description: 错误码
     */
    private final int code;

    /**
     * @description: 状态码信息
     */
    private final String message;

    /**
     * @description: 状态码描述（详情）
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
