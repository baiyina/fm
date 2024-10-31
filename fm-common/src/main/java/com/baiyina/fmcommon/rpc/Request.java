package com.baiyina.fmcommon.rpc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/27 21:13
 * @project: fm
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Request {
    String method() default POST;
    String url() default "";

    String GET = "GET";
    String POST = "POST";
}


