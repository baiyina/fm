package com.baiyina.fmclientimpl.netty.handler;

import com.baiyina.fmcommon.protocol.protobuf.FmResponseProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/11/4 22:02
 * @project: fm
 */
public class FmClientHandler extends SimpleChannelInboundHandler<FmResponseProto.FmResProto> {
    @Override
    protected void channelRead0(ChannelHandlerContext chc, FmResponseProto.FmResProto msg) throws Exception {

    }
}
