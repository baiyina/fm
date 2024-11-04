package com.baiyina.fmclientimpl.service.impl;

import com.baiyina.fmclientimpl.enums.ClientExceptionEnum;
import com.baiyina.fmclientimpl.rpc.RouterRpcManager;
import com.baiyina.fmclientimpl.service.CommandService;
import com.baiyina.fmcommon.exception.FmException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/31 16:35
 * @project: fm
 */
@Slf4j
@Service(":p2p")
public class CommandP2pServiceImpl implements CommandService {
    @Autowired
    private RouterRpcManager routerRpcManager;

    /**
     * 执行
     *
     * @param msg 可能包含的参数
     */
    @Override
    public void process(String msg) {
        String[] split = msg.split(" ");
        long receiverId;
        String msgContent;
        try{
            receiverId = Long.parseLong(split[1]);
            msgContent = split[2];
        }catch (Exception e){
            throw new FmException(ClientExceptionEnum.COMMAND_INPUT_ERROR.getCode(), "command input error");
        }
        routerRpcManager.sendP2pMsg(msgContent, receiverId);
        log.info("send p2p msg success");
    }
}
