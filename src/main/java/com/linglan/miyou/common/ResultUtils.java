package com.linglan.miyou.common;

/**
 * @description: 返回工具类
 * @author Linglan
 */
public class ResultUtils {

    /**
     * @param data 数据
     * @param <T> 任意类型
     * @description: 成功
     * @return code,data,message
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * @param code
     * @param message
     * @param description
     * @param <T>
     * @description: 成功
     * @return
     */
    public static <T> BaseResponse<T> success(int code,String message,String description ) {
        return new BaseResponse<>(code, null, message, description);
    }

    /**
     * @param errorCode
     * @description: 失败
     * @return errorCode
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @param description
     * @return
     */
    public static BaseResponse error(int code, String message, String description) {
        return new BaseResponse(code, null, message, description);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse(errorCode.getCode(), null, message, description);
    }

    /**
     * 失败
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String description) {
        return new BaseResponse(errorCode.getCode(), errorCode.getMessage(), description);
    }
}
