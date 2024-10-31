package com.baiyina.fmclientimpl.service.impl;

import com.baiyina.fmclientimpl.rpc.RouterRpcManager;
import com.baiyina.fmclientimpl.service.CommandService;
import com.baiyina.fmrouterapi.user.vo.UserLoginResVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/28 16:33
 * @project: fm
 */
@Slf4j
@Service(":login")
public class CommandLoginServiceImpl implements CommandService {
    @Autowired
    private RouterRpcManager routerRpcManager;
    @Override
    public void process(String msg) {
        String[] split = msg.split(" ");
        String username;
        String password;
        try{
            username = split[1];
            password = split[2];
        }catch (Exception e){
            throw new RuntimeException("command input error");
        }
        UserLoginResVO loginResVO = routerRpcManager.login(username, password);
        log.info("login success, userId:{}, expireTime:{}", loginResVO.getUserId(), loginResVO.getExpireTime());
    }
}
