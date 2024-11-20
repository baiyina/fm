package com.baiyina.fmclientimpl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * TODO
 *
 * @author baiyina
 * @since 1.0
 * @since 2024/11/20 15:39
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public void handleAllExceptions(Exception ex) {
        formatException(ex);
    }

    private void formatException(Exception ex) {
        ConsoleLogger.error("Exception occurred: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
    }
}