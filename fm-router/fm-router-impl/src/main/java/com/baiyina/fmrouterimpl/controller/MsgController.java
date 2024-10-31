package com.baiyina.fmrouterimpl.controller;

import com.baiyina.fmcommon.pojo.CommonResult;
import com.baiyina.fmcommon.util.CommonResultUtil;
import com.baiyina.fmrouterapi.msg.api.MsgApi;
import com.baiyina.fmrouterapi.msg.vo.FmReqMsgVO;
import com.baiyina.fmrouterapi.msg.vo.FmResMsgVO;
import com.baiyina.fmrouterimpl.service.MsgService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/31 16:21
 * @project: fm
 */
@RestController
@RequestMapping("/")
public class MsgController implements MsgApi {
    @Autowired
    private MsgService msgService;

    @Override
    @Operation(summary = "send message", description = "send message to user")
    @PostMapping("sendMsg")
    public CommonResult<FmResMsgVO> sendMsg(@RequestBody FmReqMsgVO reqMsgVO) {
        FmResMsgVO fmResMsgVO = msgService.sendMsg(reqMsgVO);
        return CommonResultUtil.success(fmResMsgVO);
    }
}
