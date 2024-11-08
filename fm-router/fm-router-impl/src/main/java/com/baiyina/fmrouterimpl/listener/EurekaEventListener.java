package com.baiyina.fmrouterimpl.listener;

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
@Component
public class EurekaEventListener {
    @EventListener
    public void handleRegisterEvent(EurekaInstanceRegisteredEvent event) {
        String serviceName = event.getInstanceInfo().getAppName();
        String instanceId = event.getInstanceInfo().getInstanceId();
        System.out.println("服务注册事件：" + serviceName + " : " + instanceId);
    }

    @EventListener
    public void handleCancelEvent(EurekaInstanceCanceledEvent event) {
        String serviceName = event.getAppName();
        String instanceId = event.getServerId();
        System.out.println("服务注销事件：" + serviceName + " : " + instanceId);

    }
}
