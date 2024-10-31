package com.baiyina.fmclientimpl.enums;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/28 15:47
 * @project: fm
 */
public enum ClientExceptionEnum {
    /**
     * 用户相关
     */
    USER_NOT_EXIST(10001, "用户不存在"),
    USER_PASSWORD_ERROR(10002, "用户名或密码错误"),
    COMMAND_INPUT_ERROR(10003, "命令输入错误"),
    ;
    private final Integer code;
    private final String message;
    ClientExceptionEnum(Integer code, String message) {
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
