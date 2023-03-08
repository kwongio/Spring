package com.blog.commuity.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("community")
                .version("v1.0.0")
                .description("API에 대한 설명 부분");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
