package com.baiyina.fmrouterimpl.utils;


import com.baiyina.fmcommon.exception.FmException;
import com.baiyina.fmcommon.pojo.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 16:54
 * @project: fm
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseBody
    public ResponseEntity<CommonResult<Void>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new CommonResult<>(400, ex.getMessage(), null));
    }

    @ExceptionHandler(value = {FmException.class})
    @ResponseBody
    public ResponseEntity<CommonResult<Void>> handleFmException(FmException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new CommonResult<>(ex.getCode(), ex.getMessage(), null));
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResponseEntity<CommonResult<Void>> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CommonResult<>(500, "服务器内部错误", null));
    }
}
