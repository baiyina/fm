package com.baiyina.fmrouterimpl.controller;

import com.baiyina.fmcommon.pojo.CommonResult;
import com.baiyina.fmcommon.util.CommonResultUtil;
import com.baiyina.fmrouterapi.user.api.UserApi;
import com.baiyina.fmrouterapi.user.vo.*;
import com.baiyina.fmrouterimpl.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/25 17:44
 * @project: fm
 */
@RestController
@RequestMapping("/")
public class UserController implements UserApi {
    @Autowired
    private UserService userService;

    @Override
    @Operation(summary = "login account", description = "login account")
    @PostMapping("login")
    public CommonResult<UserLoginResVO> login(@RequestBody UserLoginReqVO reqVo) {
        UserLoginResVO loginResVO = userService.login(reqVo);
        return CommonResultUtil.success(loginResVO);
    }

    @Override
    @Operation(summary = "get online user list", description = "get online user list")
    @PostMapping("getOnlineUserList")
    public CommonResult<List<UserVO>> getOnlineUserList() {
        List<UserVO> onlineUserList = userService.getOnlineUserList();
        return CommonResultUtil.success(onlineUserList);
    }

    @Override
    @Operation(summary = "register account", description = "register account")
    @PostMapping("register")
    public CommonResult<UserRegisterResVO> register(@RequestBody UserRegisterReqVO reqVo) {
        UserRegisterResVO registerResVO = userService.register(reqVo);
        return CommonResultUtil.success(registerResVO);
    }
}
