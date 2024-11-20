package com.baiyina.fmclientimpl.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TODO
 *
 * @author baiyina
 * @since 1.0
 * @since 2024/11/20 15:27
 */
public class ConsoleLogger {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void info(String message, Object... args) {
        System.out.println(formatMessage("INFO", message, args));
    }

    public static void error(String message, Object... args) {
        System.err.println(formatMessage("ERROR", message, args));
    }

    private static String formatMessage(String level, String message, Object... args) {
        String timestamp = LocalDateTime.now().format(DATE_FORMAT);
        String formattedMessage = String.format(message, args);
        return String.format("%s [%s] - %s", timestamp, level, formattedMessage);
    }
}
