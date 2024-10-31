package com.baiyina.fmpushserverimpl.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/31 15:23
 * @project: fm
 */
@Data
@Configuration
public class ApplicationConfig {
    @Value("${fm.push.server}")
    private String nettyServerPort;
}
