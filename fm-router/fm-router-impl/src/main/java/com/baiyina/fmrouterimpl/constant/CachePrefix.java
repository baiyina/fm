package com.baiyina.fmrouterimpl.constant;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/11/1 14:14
 * @project: fm
 */
public class CachePrefix {
    private static final String PREFIX = "fm:";
    /**
     * Struct: Set
     * Key: USER_ONLINE_CACHE_PREFIX
     * Value: userIds
     */
    public static final String USER_ONLINE_CACHE_PREFIX = PREFIX + "online_user:";

    /**
     * Struct: String
     * Key: USER_CACHE_PREFIX + userId
     * Value: userName
     */
    public static final String USER_CACHE_PREFIX = PREFIX + "user_login:";
}
