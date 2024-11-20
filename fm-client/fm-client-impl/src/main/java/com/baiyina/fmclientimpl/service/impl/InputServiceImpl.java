package com.baiyina.fmclientimpl.service.impl;

import com.baiyina.fmclientimpl.constant.Constants;
import com.baiyina.fmclientimpl.enums.*;
import com.baiyina.fmclientimpl.service.CommandService;
import com.baiyina.fmclientimpl.service.InputService;
import com.baiyina.fmclientimpl.utils.SpringBeanFactory;
import com.baiyina.fmcommon.exception.FmException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 20:26
 * @project: fm
 */
@Slf4j
@Service
public class InputServiceImpl implements InputService {

    @Override
    public void handleInput(String input) {
        //处理是否为命令，没有就是群聊
        handleCommand(input);
    }

    private void handleCommand(String input){
        if (input.startsWith(Constants.COMMAND_PREFIX)) {
            String[] command = input.split(" ");
            CommandEnum commandEnum = null;
            try {
                commandEnum = CommandEnum.getCommandEnum(command[0]);
            } catch (Exception e) {
                log.error(e.getMessage());
                return;
            }
            CommandService commandService = (CommandService) SpringBeanFactory.getBean(commandEnum.getCommandType());
            try {
                commandService.process(input);
            } catch (FmException e) {
                log.error(e.getMessage());
            } catch (Exception e) {
                //log.error("系统异常，请联系管理员, {}",e.getMessage());
            }
        }
    }
}
