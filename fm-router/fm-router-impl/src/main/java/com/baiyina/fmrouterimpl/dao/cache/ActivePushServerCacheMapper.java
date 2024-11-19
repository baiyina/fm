package com.baiyina.fmrouterimpl.dao.cache;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @description:
 *  ActivePushServerCacheMapper类负责管理活跃推送服务器的缓存操作
 *  它使用了一个内部缓存来存储服务器名称和对应的IP地址
 * @author: zhangguoa
 * @date: 2024/11/7 18:22
 * @project: fm
 */
@Slf4j
@Component
public class ActivePushServerCacheMapper {
    /**
     * 活跃推送服务器缓存，用于存储服务器名称和对应的IP地址
     */
    private final Cache<String, String> activePushServerCache;

    /**
     * 构造函数，初始化ActivePushServerCacheMapper实例
     *
     * @param activePushServerCache 由外部注入的缓存实例，用于存储服务器名称和IP地址
     */
    public ActivePushServerCacheMapper(Cache<String, String> activePushServerCache) {
        this.activePushServerCache = activePushServerCache;
    }

    /**
     * 更新缓存中的一个条目，如果条目不存在，则添加新的条目
     *
     * @param serverName 服务器名称，作为缓存的键
     * @param ipAddress  服务器IP地址，作为缓存的值
     * @param port       服务器端口号，用于拼接完整的IP地址
     */
    public void updateOneCache(String serverName, String ipAddress, Integer port) {
        if (ipAddress == null || port == null) {
            log.error("ipAddress or port is null");
            return;
        }
        try {
            String urlAddress = ipAddress + ":" + port;
            activePushServerCache.put(serverName, urlAddress);
            log.info("update cache success, serverName: {}, ipAddress: {}", serverName, urlAddress);
        } catch (Exception e) {
            log.error("update cache error, serverName: {}, ipAddress: {}", serverName, ipAddress, e);
            log.error("update cache error", e);
        }
    }

    /**
     * 从缓存中移除指定的条目
     *
     * @param serverName 要移除的服务器名称
     */
    public void removeOneCache(String serverName) {
        activePushServerCache.invalidate(serverName);
    }

    /**
     * 获取所有活跃节点的IP地址集合
     *
     * @return 包含所有活跃节点IP地址的集合
     */
    public List<String> getAllActiveNodes() {
        List<String> activeNodes = new ArrayList<>(activePushServerCache.asMap().values());
        // 去重
        return new ArrayList<>(new LinkedHashSet<>(activeNodes));
    }
}

