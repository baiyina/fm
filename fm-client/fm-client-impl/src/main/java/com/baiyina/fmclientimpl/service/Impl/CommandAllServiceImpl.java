package com.baiyina.fmclientimpl.service.Impl;

import com.baiyina.fmclientimpl.enums.CommandEnum;
import com.baiyina.fmclientimpl.service.CommandService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 23:14
 * @project: fm
 */
@Service(":all")
public class CommandAllServiceImpl implements CommandService {
    @Override
    public void process(String msg) {
        Map<String, String> allCommand = CommandEnum.getAllCommandType();
        System.out.println("---------------------------------------------------------------");
        allCommand.forEach((key, value) -> {
            // 使用 String.format 格式化输出
            System.out.println(String.format("command: %1$-20s value: %2$s", "\"" + key + "\"", value));
        });
        System.out.println("---------------------------------------------------------------");
    }

}
