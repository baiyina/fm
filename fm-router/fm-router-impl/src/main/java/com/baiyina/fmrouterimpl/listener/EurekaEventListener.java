package com.baiyina.fmrouterimpl.listener;

import com.baiyina.fmrouterimpl.dao.cache.ActivePushServerCacheMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/11/7 18:55
 * @project: fm
 */
@Slf4j
@Component
public class EurekaEventListener {
    private final ActivePushServerCacheMapper activePushServerCacheMapper;
    public EurekaEventListener(ActivePushServerCacheMapper activePushServerCacheMapper) {
        this.activePushServerCacheMapper = activePushServerCacheMapper;
    }
    @EventListener
    public void handleRegisterEvent(EurekaInstanceRegisteredEvent event) {
        String instanceId = event.getInstanceInfo().getInstanceId();
        String urlAddress = event.getInstanceInfo().getIPAddr();
        Integer port = event.getInstanceInfo().getPort();
        activePushServerCacheMapper.updateOneCache(instanceId, urlAddress, port);
        log.info("服务注册事件：" + instanceId + " : " + urlAddress );
    }

    @EventListener
    public void handleCancelEvent(EurekaInstanceCanceledEvent event) {
        String serviceName = event.getAppName();
        String instanceId = event.getServerId();
        activePushServerCacheMapper.removeOneCache(instanceId);
        log.info("服务注销事件：" + serviceName + " : " + instanceId);
    }
}
