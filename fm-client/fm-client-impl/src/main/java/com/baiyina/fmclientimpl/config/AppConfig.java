package com.baiyina.fmclientimpl.config;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/28 14:31
 * @project: fm
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppConfig {
    @Value("${fm.router.url}")
    private String routerUrl;


}
