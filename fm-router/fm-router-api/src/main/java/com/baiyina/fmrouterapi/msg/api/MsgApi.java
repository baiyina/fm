package com.baiyina.fmrouterapi.msg.api;

import com.baiyina.fmcommon.pojo.CommonResult;
import com.baiyina.fmrouterapi.msg.vo.FmReqMsgVO;
import com.baiyina.fmrouterapi.msg.vo.FmResMsgVO;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/31 16:16
 * @project: fm
 */
public interface MsgApi {
    /**
     * 发送消息
     * @param reqMsgVO 消息体 + 发送者 + 接收者列表 + 消息类型
     * @return 发送结果
     */
    CommonResult<FmResMsgVO> sendMsg(FmReqMsgVO reqMsgVO);
}
