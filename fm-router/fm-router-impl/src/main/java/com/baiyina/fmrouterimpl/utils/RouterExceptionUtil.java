package com.baiyina.fmrouterimpl.utils;

import com.baiyina.fmcommon.exception.FmException;
import com.baiyina.fmrouterimpl.enums.RouterExceptionEnum;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 16:32
 * @project: fm
 */
public class RouterExceptionUtil {
    public static void handlerException (RouterExceptionEnum exceptionEnum) {
        throw new FmException(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }
}
