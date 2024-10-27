package com.baiyina.fmclientimpl.service;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 20:52
 * @project: fm
 */
public interface CommandService {
    /**
     * 执行
     * @param msg 可能包含的参数
     */
    void process(String msg);
}
