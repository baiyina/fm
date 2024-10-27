package com.baiyina.fmclientimpl.enums;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 20:39
 * @project: fm
 */
public enum CommandEnum {
    /**
     * 用户输入命令
     */
    ALL(":all", "获取所有命令"),
    REGISTER(":register", "注册"),
    LOGIN(":login", "登录"),
    ONLINE_USER(":ol", "获取在线用户"),
    SEND_P2P(":p2p", "私聊"),
    EXIT(":exit", "退出"),
    ;

    private final String commandType;
    private final String message;
    CommandEnum(String commandType, String message) {
        this.commandType = commandType;
        this.message = message;
    }

    public static CommandEnum getCommandEnum(String s) {
        for (CommandEnum commandEnum : CommandEnum.values()) {
            if (commandEnum.getCommandType().equals(s)) {
                return commandEnum;
            }
        }
        return null;
    }

    public static Map<String, String> getAllCommandType() {
        Map<String, String> map = new HashMap<>();
        for (CommandEnum commandEnum : CommandEnum.values()) {
            map.put(commandEnum.getCommandType(), commandEnum.getMessage());
        }
        return map;
    }

    public String getCommandType() {
        return commandType;
    }

    public String getMessage() {
        return message;
    }
}
