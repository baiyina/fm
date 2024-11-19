package com.baiyina.fmrouterimpl.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/11/8 21:56
 * @project: fm
 */
@Data
@Configuration
public class ApplicationConfig {
    @Value("${fm.router.load_balancing.consistent_hash.virtual_node_num:10}")
    private Integer consistentHashVirtualNodeNum;
}
