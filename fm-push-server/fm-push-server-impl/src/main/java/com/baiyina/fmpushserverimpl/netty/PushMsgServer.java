package com.baiyina.fmpushserverimpl.netty;

import com.baiyina.fmcommon.protocol.protobuf.FmRequestProto;
import com.baiyina.fmpushserverimpl.config.ApplicationConfig;
import com.baiyina.fmpushserverimpl.netty.handler.FmPushServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/30 22:10
 * @project: fm
 */
@Slf4j
public class PushMsgServer {

    private int nettyPort;
    @Autowired
    private ApplicationConfig applicationConfig;
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    @PostConstruct
    @SneakyThrows
    public void start() {
        nettyPort = applicationConfig.getNettyServerPort();
        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .localAddress(new InetSocketAddress(nettyPort))
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<Channel>() {

                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline()
                                .addLast(new IdleStateHandler(11,
                                        0,0))
                                .addLast(new ProtobufVarint32FrameDecoder())
                                .addLast(new ProtobufDecoder(FmRequestProto.FmReqProto.getDefaultInstance()))
                                .addLast(new ProtobufVarint32LengthFieldPrepender())
                                .addLast(new ProtobufEncoder())
                                .addLast(new FmPushServerHandler());
                    }
                });
        CompletableFuture<Channel> completableFuture = new CompletableFuture<>();
        serverBootstrap.bind().sync().addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                log.info("netty server start success");
                completableFuture.complete(future.channel());
            } else {
                log.error("netty server start failed");
                completableFuture.completeExceptionally(future.cause());
            }
        });
    }
}
