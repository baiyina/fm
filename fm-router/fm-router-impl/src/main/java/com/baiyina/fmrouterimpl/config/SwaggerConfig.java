package com.baiyina.fmrouterimpl.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: TODO
 * @author: zhangguoa
 * @date: 2024/10/26 17:10
 * @project: fm
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI createRestApi() {
        return new OpenAPI()
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("fm-router")
                .description("cim-forward-router api")
                .contact(contact())
                .version("1.0.0");
    }

    private Contact contact () {
        Contact contact = new Contact();
        contact.setName("baiyina");
        return contact;
    }
}
