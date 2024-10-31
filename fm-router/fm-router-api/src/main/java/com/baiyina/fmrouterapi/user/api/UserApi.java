package com.baiyina.fmrouterapi.user.api;

import com.baiyina.fmcommon.pojo.CommonResult;
import com.baiyina.fmrouterapi.user.vo.UserLoginReqVO;
import com.baiyina.fmrouterapi.user.vo.UserLoginResVO;
import com.baiyina.fmrouterapi.user.vo.UserRegisterReqVO;
import com.baiyina.fmrouterapi.user.vo.UserRegisterResVO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/28 13:29
 * @project: fm
 */
public interface UserApi {
    /**
     * 注册
     * @param reqVo 用户注册信息
     * @return 用户id + password
     */
    CommonResult<UserRegisterResVO> register(UserRegisterReqVO reqVo);

    /**
     * 登录
     * @param reqVo 用户登录信息
     * @return 用户id + expireTime
     */
    CommonResult<UserLoginResVO> login(UserLoginReqVO reqVo);
}
