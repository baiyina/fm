package com.baiyina.fmcommon.rpc;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

/**
 * @description: HTTP 客户端工具类，用于发送 POST 和 GET 请求
 * @author: zhangguoa
 * @date: 2024/10/27 21:32
 * @project: fm
 */
@Slf4j
public final class HttpClient {

    private static final MediaType MEDIA_TYPE = MediaType.get("application/json");

    /**
     * 发送 POST 请求
     *
     * @param okHttpClient OkHttpClient 实例
     * @param params       请求参数
     * @param url          请求 URL
     * @return 响应对象
     * @throws IOException 如果请求失败
     */
    public static Response post(OkHttpClient okHttpClient, String params, String url) throws IOException {
        if (okHttpClient == null) {
            throw new IllegalArgumentException("OkHttpClient cannot be null");
        }
        RequestBody requestBody = RequestBody.create(params, MEDIA_TYPE);

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            log.error("Error occurred while sending request: " + e.getMessage());
        }
        if (!response.isSuccessful()) {
            String errorMessage = "RPC failed with status code " + response.code() + ": " + response.body().string();
            log.error(errorMessage);
        }

        return response;
    }

    /**
     * 发送 GET 请求
     *
     * @param okHttpClient OkHttpClient 实例
     * @param url          请求 URL
     * @return 响应对象
     * @throws IOException 如果请求失败
     */
    public static Response get(OkHttpClient okHttpClient, String url) throws IOException {
        if (okHttpClient == null) {
            throw new IllegalArgumentException("OkHttpClient cannot be null");
        }
        okhttp3.Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            log.error("Error occurred while sending request: " + e.getMessage());
        }
        if (!response.isSuccessful()) {
            String errorMessage = "Unexpected code " + response.code() + ": " + response.body().string();
            log.error(errorMessage);
        }

        return response;
    }
}
  