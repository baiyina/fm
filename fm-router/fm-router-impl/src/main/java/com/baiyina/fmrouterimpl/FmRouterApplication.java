package com.baiyina.fmrouterimpl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ll
 */
@EnableEurekaServer
@EnableCaching
@SpringBootApplication
@MapperScan("com.baiyina.fmrouterimpl.dao.mapper")
public class FmRouterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmRouterApplication.class, args);
    }

}
