package com.baiyina.fmrouterimpl.service;

/**
 * The RouterService interface is used for routing.
 * It provides a method to determine the route key.
 *
 * @author: zhangguoa
 * @date: 2024/11/8 22:11
 * @project: fm
 */
public interface RouterService {
    /**
     * Determines the route based on the specified key.
     *
     * @param key The key used for routing, the specific meaning depends on the implementation.
     * @return Returns the string representation of the route.
     */
    String route(String key);
}
