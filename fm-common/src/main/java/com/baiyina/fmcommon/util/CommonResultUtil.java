package com.baiyina.fmcommon.util;

import com.baiyina.fmcommon.enums.ResultStatusEnum;
import com.baiyina.fmcommon.pojo.CommonResult;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/25 10:46
 * @project: fm
 */
public class CommonResultUtil {
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getMessage() ,data);
    }
    public static <T> CommonResult<T> error(T data) {
        return new CommonResult<>(ResultStatusEnum.ERROR.getCode(), ResultStatusEnum.ERROR.getMessage(), data);
    }
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<>(ResultStatusEnum.SUCCESS.getCode(), message, data);
    }
    public static <T> CommonResult<T> error(Integer code, String message) {
        return new CommonResult<>(code, message, null);
    }
    public static <T> CommonResult<T> error(ResultStatusEnum resultStatusEnum) {
        return new CommonResult<>(resultStatusEnum.getCode(), resultStatusEnum.getMessage(), null);
    }

}
