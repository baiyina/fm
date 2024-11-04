package com.baiyina.fmcommon.enums;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/31 16:13
 * @project: fm
 */
public enum FmMsgEnum {
    /**
     * 消息枚举
     */
    P2P(0, "私聊"),
    GROUP(1, "群聊"),
    PING(2, "心跳"),
    EXIT(3, "退出"),
    ;
    private final Integer code;
    private final String message;
    FmMsgEnum(Integer code, String message) {
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
