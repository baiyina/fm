package com.baiyina.fmrouterimpl.controller;

import com.baiyina.fmcommon.pojo.CommonResult;
import com.baiyina.fmcommon.util.ResultUtil;
import com.baiyina.fmrouterapi.RouterApi;
import com.baiyina.fmrouterimpl.controller.vo.UserLoginReqVO;
import com.baiyina.fmrouterimpl.controller.vo.UserLoginResVO;
import com.baiyina.fmrouterimpl.controller.vo.UserRegisterReqVO;
import com.baiyina.fmrouterimpl.controller.vo.UserRegisterResVO;
import com.baiyina.fmrouterimpl.service.UserService;
import com.google.protobuf.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/25 17:44
 * @project: fm
 */
@RestController
@RequestMapping("/")
public class UserController implements RouterApi {
    @Autowired
    private UserService userService;
    @Operation(summary = "login account")
    CommonResult<UserLoginResVO> login(UserLoginReqVO reqVo) {
        UserLoginResVO loginResVO = userService.login(reqVo);
        return null;
    }

    @Operation(summary = "register account")
    @PostMapping("register")
    CommonResult<UserRegisterResVO> register(@RequestBody UserRegisterReqVO reqVo) {
        UserRegisterResVO registerResVO = userService.register(reqVo);
        return ResultUtil.success(registerResVO);
    }
}
