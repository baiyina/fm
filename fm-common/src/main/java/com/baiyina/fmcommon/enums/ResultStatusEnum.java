package com.baiyina.fmcommon.enums;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/25 10:48
 * @project: fm
 */
public enum ResultStatusEnum {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    /**
     * 失败
     */
    FAIL(400, "失败"),
    /**
     * 服务器异常
     */
    ERROR(500, "服务器异常");
    private final Integer code;
    private final String message;
    ResultStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
