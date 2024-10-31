package com.baiyina.fmclientimpl.service.impl;

import com.baiyina.fmclientimpl.enums.ClientExceptionEnum;
import com.baiyina.fmclientimpl.rpc.RouterRpcManager;
import com.baiyina.fmclientimpl.service.CommandService;
import com.baiyina.fmcommon.exception.FmException;
import com.baiyina.fmrouterapi.user.vo.UserRegisterResVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/27 0:13
 * @project: fm
 */
@Slf4j
@Service(":register")
public class CommandRegisterServiceImpl implements CommandService {
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
            throw new FmException(ClientExceptionEnum.COMMAND_INPUT_ERROR.getCode(), "command input error");
        }

        UserRegisterResVO register = routerRpcManager.register(username, password);
        log.info("register success, userId:{}, token:{}", register.getUserId(), register.getToken());
    }
}
