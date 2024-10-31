package com.baiyina.fmrouterimpl.service;

import com.baiyina.fmrouterapi.msg.vo.FmReqMsgVO;
import com.baiyina.fmrouterapi.msg.vo.FmResMsgVO;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/31 16:27
 * @project: fm
 */
public interface MsgService {
    /**
     * 发送消息
     * @param reqMsgVO 请求消息
     * @return 消息响应
     */
    FmResMsgVO sendMsg(FmReqMsgVO reqMsgVO);
}
