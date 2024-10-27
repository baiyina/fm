package com.baiyina.fmrouterimpl.service;

import com.baiyina.fmrouterimpl.controller.vo.UserLoginReqVO;
import com.baiyina.fmrouterimpl.controller.vo.UserLoginResVO;
import com.baiyina.fmrouterimpl.controller.vo.UserRegisterReqVO;
import com.baiyina.fmrouterimpl.controller.vo.UserRegisterResVO;
import com.baiyina.fmrouterimpl.dao.dos.UserDO;
import com.baiyina.fmrouterimpl.dao.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.IService;

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

    UserLoginResVO login(UserLoginReqVO reqVo);
}
