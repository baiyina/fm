package com.baiyina.fmrouterimpl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ll
 */
@SpringBootApplication
@MapperScan("com.baiyina.fmrouterimpl.dao.mapper")
public class FmRouterBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmRouterBizApplication.class, args);
    }

}
