package com.baiyina.fmclientimpl.service.impl;

import com.baiyina.fmclientimpl.rpc.RouterRpcManager;
import com.baiyina.fmclientimpl.service.CommandService;
import com.baiyina.fmrouterapi.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/11/1 18:15
 * @project: fm
 */
@Service(":ol")
public class CommandOnlineUserServiceImpl implements CommandService {
    @Autowired
    private RouterRpcManager routerRpcManager;

    @Override
    public void process(String msg) {
        try {
            List<UserVO> onlineUserList = routerRpcManager.getOnlineUserList();
            System.out.println("online user list: " + onlineUserList);
        } catch (Exception e) {
            System.out.println("获取在线用户列表失败");
        }
    }
}
