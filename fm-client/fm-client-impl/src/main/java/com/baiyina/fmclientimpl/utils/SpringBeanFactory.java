package com.baiyina.fmclientimpl.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 20:28
 * @project: fm
 */
@Component
public class SpringBeanFactory implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringBeanFactory.applicationContext = applicationContext;
    }
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }
}
