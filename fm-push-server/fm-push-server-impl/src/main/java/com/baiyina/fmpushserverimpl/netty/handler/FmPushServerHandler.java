package com.baiyina.fmpushserverimpl.netty.handler;

import com.baiyina.fmcommon.protocol.protobuf.FmRequestProto;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/31 15:45
 * @project: fm
 */
@ChannelHandler.Sharable
@Slf4j
public class FmPushServerHandler extends SimpleChannelInboundHandler<FmRequestProto.FmReqProto> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FmRequestProto.FmReqProto msg) throws Exception {
        log.info("receive msg: {}", msg.toString());
    }
}
