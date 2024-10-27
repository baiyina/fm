package com.baiyina.fmrouterimpl.enums;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 16:22
 * @project: fm
 */
public enum RouterExceptionEnum {
    /**
     * 路由注册参数无效
     */
    ROUTER_REGISTER_INVALID_INPUT_EXCEPTION(400, "用户注册参数无效"),
    /**
     * 路由注册异常
     */
    ROUTER_REGISTER_NAME_DUPLICATION_EXCEPTION(500, "用户名重复异常"),

    ROUTER_LOGIN_INVALID_INPUT_EXCEPTION(400, "用户注册参数无效"),

    ROUTER_LOGIN_PASSWORD_ERROR_EXCEPTION(500, "密码错误"),

    ROUTER_LOGIN_USER_NOT_EXIST_EXCEPTION(500, "用户不存在"),
    ;

    private final Integer code;
    private final String message;

    RouterExceptionEnum(Integer code, String message) {
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
