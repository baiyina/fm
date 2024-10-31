package com.baiyina.fmclientimpl.rpc;

import com.baiyina.fmclientimpl.utils.SpringBeanFactory;
import com.baiyina.fmcommon.enums.ResultStatusEnum;
import com.baiyina.fmcommon.pojo.CommonResult;
import com.baiyina.fmcommon.rpc.RpcManager;
import com.baiyina.fmrouterapi.user.api.UserApi;
import com.baiyina.fmrouterapi.user.vo.UserLoginReqVO;
import com.baiyina.fmrouterapi.user.vo.UserLoginResVO;
import com.baiyina.fmrouterapi.user.vo.UserRegisterReqVO;
import com.baiyina.fmrouterapi.user.vo.UserRegisterResVO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/28 13:36
 * @project: fm
 */
@Slf4j
public class RouterRpcManager {
    private final UserApi userApi;

    public RouterRpcManager(String routerUrl, OkHttpClient okHttpClient) {
        userApi = RpcManager.create(UserApi.class, routerUrl, okHttpClient);
    }

    public UserRegisterResVO register(String username, String password) {
        CommonResult<UserRegisterResVO> registerRes = userApi.register(new UserRegisterReqVO(username, password));
        if (registerRes.getCode().equals(ResultStatusEnum.SUCCESS.getCode())) {
            log.info(registerRes.getMessage());
        }
        return registerRes.getData();
    }

    public UserLoginResVO login(String username, String password) {
        CommonResult<UserLoginResVO> loginRes = userApi.login(new UserLoginReqVO(username, password));
        if (loginRes.getCode().equals(ResultStatusEnum.SUCCESS.getCode())) {
            log.info(loginRes.getMessage());
        }
        return loginRes.getData();
    }

}
