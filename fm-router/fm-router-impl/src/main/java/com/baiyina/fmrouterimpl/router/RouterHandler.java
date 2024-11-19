package com.baiyina.fmrouterimpl.router;

import java.util.List;

/**
 * RouterHandler接口用于定义路由处理的规则
 * 它提供了一个方法来根据给定的键和值列表进行路由决策
 * 主要用于在不同的执行路径或服务端点之间进行选择
 *
 * @author zhangguoa
 * @date 2024/11/8 12:18
 * @project fm
 */
public interface RouterHandler {
    /**
     * 根据提供的值列表和键来决定路由的目标
     * 具体的路由逻辑由实现该接口的类来实现
     *
     * @param values 一个字符串列表，包含了用于路由决策的值
     * @param key 用于路由决策的键
     * @return 返回一个字符串，表示路由的目标或结果
     */
    String route(List<String> values, String key);
}

