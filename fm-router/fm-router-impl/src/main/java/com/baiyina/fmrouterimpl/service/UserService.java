package com.baiyina.fmrouterimpl.service;

import com.baiyina.fmrouterapi.user.vo.UserLoginReqVO;
import com.baiyina.fmrouterapi.user.vo.UserLoginResVO;
import com.baiyina.fmrouterapi.user.vo.UserRegisterReqVO;
import com.baiyina.fmrouterapi.user.vo.UserRegisterResVO;

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
}
