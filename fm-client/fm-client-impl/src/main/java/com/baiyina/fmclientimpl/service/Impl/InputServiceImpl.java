package com.baiyina.fmclientimpl.service.Impl;

import com.baiyina.fmclientimpl.enums.CommandEnum;
import com.baiyina.fmclientimpl.service.CommandService;
import com.baiyina.fmclientimpl.service.InputService;
import com.baiyina.fmclientimpl.utils.SpringBeanFactory;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 20:26
 * @project: fm
 */
@Service
public class InputServiceImpl implements InputService {

    @Override
    public void handleInput(String input) {
        //处理是否为命令，没有就是群聊
        handleCommand(input);
    }

    private void handleCommand(String input){
        if (input.startsWith(":")) {
            String[] command = input.split(" ");
            CommandEnum commandEnum = CommandEnum.getCommandEnum(command[0]);
            if (commandEnum == null) {
                return;
            }
            CommandService commandService = (CommandService) SpringBeanFactory.getBean(commandEnum.getCommandType());
            commandService.process(input);
        }
    }
}
