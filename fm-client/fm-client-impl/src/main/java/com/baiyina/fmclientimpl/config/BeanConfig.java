package com.baiyina.fmclientimpl.config;

import com.baiyina.fmclientimpl.rpc.RouterRpcManager;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/28 12:00
 * @project: fm
 */
@Configuration
public class BeanConfig {
    @Autowired
    private AppConfig appConfig;
    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置连接超时时间为3秒
        builder.connectTimeout(3, TimeUnit.SECONDS)
                // 设置读取超时时间为3秒
                .readTimeout(3, TimeUnit.SECONDS)
                // 设置写入超时时间为3秒
                .writeTimeout(3, TimeUnit.SECONDS)
                // 设置在连接失败时自动重试
                .retryOnConnectionFailure(true);
        return builder.build();
    }

    @Bean
    public RouterRpcManager routerRpcManager(OkHttpClient okHttpClient) {
        return new RouterRpcManager(appConfig.getRouterUrl(), okHttpClient);
    }
}
