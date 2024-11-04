package com.baiyina.fmrouterimpl.service;

import com.baiyina.fmrouterapi.user.vo.*;

import java.util.List;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 14:13
 * @project: fm
 */
public interface UserService{
    /**
     * 注册
     * @param reqVo username & password
     * @return username & token
     */
    UserRegisterResVO register(UserRegisterReqVO reqVo);

    /**
     * 登录
     * @param reqVo username & password
     * @return userid & expireTime
     */
    UserLoginResVO login(UserLoginReqVO reqVo);

    /**
     * 获取在线用户列表
     * @return userList
     */
    List<UserVO> getOnlineUserList();
}
