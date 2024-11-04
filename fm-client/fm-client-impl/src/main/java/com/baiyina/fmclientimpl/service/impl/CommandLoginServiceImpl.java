package com.baiyina.fmclientimpl.service.impl;

import com.baiyina.fmclientimpl.config.AppConfig;
import com.baiyina.fmclientimpl.constant.CurrentUser;
import com.baiyina.fmclientimpl.enums.ClientExceptionEnum;
import com.baiyina.fmclientimpl.rpc.RouterRpcManager;
import com.baiyina.fmclientimpl.service.CommandService;
import com.baiyina.fmcommon.exception.FmException;
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
            throw new FmException(ClientExceptionEnum.COMMAND_INPUT_ERROR.getCode(),
                    "command input error");
        }
        UserLoginResVO loginResVO = routerRpcManager.login(username, password);
        CurrentUser.setCurrentUser(loginResVO.getUserId(), username);
        log.info("login success, userId:{}, expireTime:{}", loginResVO.getUserId(), loginResVO.getExpireTime());
    }
}
