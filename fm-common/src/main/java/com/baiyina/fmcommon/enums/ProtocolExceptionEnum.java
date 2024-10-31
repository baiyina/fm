package com.baiyina.fmcommon.enums;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/30 21:29
 * @project: fm
 */
public enum ProtocolExceptionEnum {
    /**
     * 协议相关异常
     */
    PROTOCOL_INVALID_INPUT_EXCEPTION(400, "协议参数无效"),
    PROTOCOL_INVALID_EXCEPTION(500, "协议无效"),
    PROTOCOL_NOT_SUPPORT_EXCEPTION(501, "协议不支持"),
    PROTOCOL_NOT_FOUND_EXCEPTION(404, "协议不存在"),
    PROTOCOL_SERIALIZE_EXCEPTION(500, "协议序列化异常"),
    PROTOCOL_DESERIALIZE_EXCEPTION(500, "协议反序列化异常")
    ;
    private final Integer code;
    private final String message;
    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    ProtocolExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
