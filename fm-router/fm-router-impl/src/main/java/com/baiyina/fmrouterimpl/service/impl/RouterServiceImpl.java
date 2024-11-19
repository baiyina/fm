package com.baiyina.fmrouterimpl.service.impl;

import com.baiyina.fmrouterimpl.dao.cache.ActivePushServerCacheMapper;
import com.baiyina.fmrouterimpl.router.RouterHandler;
import com.baiyina.fmrouterimpl.service.RouterService;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/11/8 22:12
 * @project: fm
 */
@Service
public class RouterServiceImpl implements RouterService {

    private final ActivePushServerCacheMapper activePushServerCacheMapper;
    private final RouterHandler routerHandler;

    public RouterServiceImpl(RouterHandler routerHandler, ActivePushServerCacheMapper activePushServerCacheMapper) {
        this.routerHandler = routerHandler;
        this.activePushServerCacheMapper = activePushServerCacheMapper;
    }
    @Override
    public String route(String key) {
        return routerHandler.route(activePushServerCacheMapper.getAllActiveNodes(), key);
    }
}
