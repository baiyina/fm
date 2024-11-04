package com.baiyina.fmclientimpl.rpc;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.baiyina.fmclientimpl.constant.CurrentUser;
import com.baiyina.fmcommon.enums.FmMsgEnum;
import com.baiyina.fmcommon.enums.ResultStatusEnum;
import com.baiyina.fmcommon.pojo.CommonResult;
import com.baiyina.fmcommon.rpc.RpcManager;
import com.baiyina.fmrouterapi.msg.api.MsgApi;
import com.baiyina.fmrouterapi.msg.vo.FmReqMsgVO;
import com.baiyina.fmrouterapi.msg.vo.FmResMsgVO;
import com.baiyina.fmrouterapi.user.api.UserApi;
import com.baiyina.fmrouterapi.user.vo.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/28 13:36
 * @project: fm
 */
@Slf4j
public class RouterRpcManager {
    private final UserApi userApi;

    private final MsgApi msgApi;

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder()
            .setNamePrefix("fm-client-rpc-thread-pool")
            .setDaemon(true)
            .build();

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            10, 100, 60, java.util.concurrent.TimeUnit.SECONDS,
            new java.util.concurrent.LinkedBlockingQueue<>(100),
            THREAD_FACTORY,
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    public RouterRpcManager(String routerUrl, OkHttpClient okHttpClient) {
        userApi = RpcManager.create(UserApi.class, routerUrl, okHttpClient);
        msgApi = RpcManager.create(MsgApi.class, routerUrl, okHttpClient);
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

    public void sendP2pMsg(String msg, Long receiverId) {
        List<Long> receiverIds = new ArrayList<>();
        receiverIds.add(receiverId);
        CompletableFuture.runAsync(()->{
            try {
                CommonResult<FmResMsgVO> result = msgApi.sendMsg(new FmReqMsgVO(FmMsgEnum.P2P.getCode(),
                        CurrentUser.getCurrentUser().getUserId(), receiverIds, msg));
                if (result.getCode().equals(ResultStatusEnum.SUCCESS.getCode())) {
                    //TODO: 此处应该以写入日志文件的方式做，而不是写控制台
                    log.info("发送消息成功");
                }
            } catch (Exception e) {
                log.error("发送消息失败", e);
            }
        }, THREAD_POOL_EXECUTOR);
    }

    public List<UserVO> getOnlineUserList() {
        CommonResult<List<UserVO>> onlineUserList = userApi.getOnlineUserList();
        if (onlineUserList.getCode().equals(ResultStatusEnum.SUCCESS.getCode())) {
            log.info(onlineUserList.getMessage());
        } else {
            log.error("获取在线用户失败");
        }
        return onlineUserList.getData();
    }
}
