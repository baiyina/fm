package com.baiyina.fmcommon.rpc;

import cn.hutool.core.lang.ParameterizedTypeImpl;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baiyina.fmcommon.rpc.HttpClient;
import com.baiyina.fmcommon.rpc.Request;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

/**
 * @param <T> 表示动态代理的接口类型
 * @description: RpcManager类负责处理与远程服务的通信它使用OkHttpClient来发送请求，并利用Java的动态代理来处理接口的远程调用
 * @author: zhangguoa
 * @date: 2024/10/27 0:15
 * @project: fm
 */
@Slf4j
public final class RpcManager<T> {
    /**
     * 动态代理的接口类
     */
    private Class<T> clazz;
    /**
     * 远程服务的URL
     */
    private String url;
    /**
     * HTTP客户端，用于发送请求
     */
    private OkHttpClient client;

    /**
     * 构造函数，初始化RpcManager实例
     */
    private RpcManager(Class<T> clazz, String url, OkHttpClient client) {
        this.clazz = clazz;
        this.url = url;
        this.client = client;
    }

    /**
     * 创建RpcManager实例的工厂方法
     * @param clazz 动态代理的接口类
     * @param url 远程服务的URL
     * @return 返回动态代理的接口实例
     */
    public static <T> T create(Class<T> clazz, String url) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return create(clazz, url, builder.build());
    }

    /**
     * 创建RpcManager实例的工厂方法，允许自定义OkHttpClient
     * @param clazz 动态代理的接口类
     * @param url 远程服务的URL
     * @param client 自定义的OkHttpClient实例
     * @return 返回动态代理的接口实例
     */
    public static <T> T create(Class<T> clazz, String url, OkHttpClient client) {
        return new RpcManager<>(clazz, url, client).getProxyInstance();
    }

    /**
     * 生成动态代理实例的方法
     * @return 返回动态代理的接口实例
     */
    @SuppressWarnings("unchecked")
    private T getProxyInstance() {
        return (T) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{clazz },
                new ProxyInvalidationHandler());
    }

    /**
     * 实现InvocationHandler接口来处理远程调用
     */

    private class ProxyInvalidationHandler implements InvocationHandler {

        /**
         * 处理代理对象的方法调用
         * @param proxy 代理对象
         * @param method 被调用的方法
         * @param args 方法参数
         * @return 返回方法的执行结果
         * @throws Throwable 如果方法执行过程中出现异常
         */
        @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Response response = null;
            String serverUrl = buildServerUrl(method);
            try {
                if (isGetRequest(method)) {
                    response = HttpClient.get(client, serverUrl);
                } else {
                    response = sendPostRequest(method, args, serverUrl);
                }
                return handleResponse(method,response);
            } finally {
                closeResponse(response);
            }
        }

        // 关闭Response对象，释放资源
        private void closeResponse(Response response) {
            if (response != null) {
                if (response.body() != null) {
                    response.body().close();
                }
            }
        }

        // 处理服务器返回的响应
        private Object handleResponse(Method method, Response response) {
            if (method.getReturnType() == void.class) {
                return null;
            }
            String json = null;
            if (response.body() != null) {
                try {
                    json = response.body().string();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            Type genericTypeOfCommonResponse = getGenericTypeOfCommonResponse(method);
            if (genericTypeOfCommonResponse == null) {
                return JSONUtil.toBean(json, method.getReturnType());
            } else {
                ParameterizedTypeImpl commonResponse = new ParameterizedTypeImpl(
                        new Type[]{genericTypeOfCommonResponse},
                        null,
                        method.getReturnType()
                );
                return JSONUtil.toBean(json, commonResponse, true);
            }
        }

        // 获取方法返回值的泛型类型
        private Type getGenericTypeOfCommonResponse(Method method) {
            Type genericReturnType = method.getGenericReturnType();
            if (genericReturnType instanceof ParameterizedType parameterizedType) {
                return parameterizedType.getActualTypeArguments()[0];
            }
            return null;
        }

        // 发送POST请求到远程服务
        private Response sendPostRequest(Method method, Object[] args, String serverUrl) {
            JSONObject jsonObject = new JSONObject();
            validateArgs(args);
            if (method.getParameterTypes().length > 0) {

                Object para = args[0];
                Class<?> parameterType = method.getParameterTypes()[0];
                for (Field field : parameterType.getDeclaredFields()) {
                    field.setAccessible(true);
                    try {
                        jsonObject.set(field.getName(), field.get(para));
                    } catch (IllegalAccessException e) {
                        log.error( "Failed to access field: " + field.getName(), e);
                    }
                }
            }
            try {
                return HttpClient.post(client, jsonObject.toString(), serverUrl);
            } catch (IOException e) {
                log.error("Failed to send POST request to server: " + serverUrl, e);
                throw new RuntimeException("Failed to send POST request", e);
            }
        }

        // 校验方法参数，目前未实现具体逻辑
        private void validateArgs(Object[] args) {
            if (args != null && args.length > 1) {
                throw new IllegalArgumentException("参数校验失败");
            }
        }

        // 判断方法是否是GET请求
        private boolean isGetRequest(Method method) {
            Request annotation = method.getAnnotation(Request.class);
            return annotation != null && annotation.method().equals(Request.GET);
        }

        // 构建远程服务的完整URL
        private String buildServerUrl(Method method) throws URISyntaxException {
            String serverUrl = url + "/" + method.getName();
            Request annotation = method.getAnnotation(Request.class);
            if (annotation != null && StrUtil.isNotEmpty(annotation.url())) {
                serverUrl = url + "/" + annotation.url();
            }
            URI serverUri = new URI(serverUrl);
            serverUrl = serverUri.normalize().toString();
            return serverUrl;
        }
    }
}
