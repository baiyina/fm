package com.baiyina.fmclientimpl.constant;

import com.baiyina.fmclientimpl.enums.ClientExceptionEnum;
import com.baiyina.fmcommon.exception.FmException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 当前用户信息类，用于存储当前用户的ID和用户名。
 * 提供线程安全的方法来获取和设置当前用户信息。
 *
 * @author: zhangguoa
 * @date: 2024/10/31 21:52
 * @project: fm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUser {
    private Long userId;
    private String userName;

    private static final AtomicReference<CurrentUser> currentUser = new AtomicReference<>();
    private static final Logger log = LoggerFactory.getLogger(CurrentUser.class);

    /**
     * 设置当前用户信息。
     *
     * @param userId 用户ID
     * @param userName 用户名
     */
    public static void setCurrentUser(Long userId, String userName) {
        if (userId == null || userName == null) {
            throw new FmException(ClientExceptionEnum.USER_NOT_LOGIN.getCode(),
                    ClientExceptionEnum.USER_NOT_LOGIN.getMessage());
        }
        currentUser.set(new CurrentUser(userId, userName));
        log.info("设置当前用户信息: userId={}, userName={}", userId, userName);
    }

    /**
     * 获取当前用户信息。
     *
     * @return 当前用户信息，如果未设置则返回null
     */
    public static CurrentUser getCurrentUser() {
        CurrentUser user = currentUser.get();
        if (user == null) {
            log.warn("当前用户信息未设置");
            // 可以选择返回一个默认的CurrentUser对象或抛出异常
            // return new CurrentUser(null, null); // 返回默认对象
            throw new FmException(ClientExceptionEnum.USER_NOT_LOGIN.getCode(),
                    ClientExceptionEnum.USER_NOT_LOGIN.getMessage());
        }
        return user;
    }

    /**
     * 清除当前用户信息。
     */
    public static void clearCurrentUser() {
        currentUser.set(null);
        log.info("清除当前用户信息");
    }

    /**
     * 获取用户ID。
     *
     * @return 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 获取用户名。
     *
     * @return 用户名
     */
    private String getUserName() {
        return userName;
    }
}
