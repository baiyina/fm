package com.baiyina.fmrouterimpl.dao.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.netflix.discovery.EurekaClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/11/7 18:22
 * @project: fm
 */
@Component
public class ActivePushServerCacheMapper {
    private final Cache<String, String> activePushServerCache;

    public ActivePushServerCacheMapper(Cache<String, String> activePushServerCache) {
        this.activePushServerCache = activePushServerCache;
    }

//    public List<String> getActivePushServerList() {
//        List<String> activePushServerList = activePushServerCache.asMap().values().stream().toList();
//        activePushServerList.
//    }

}
