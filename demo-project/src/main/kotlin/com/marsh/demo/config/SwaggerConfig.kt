package com.marsh.demo.config

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {
    @Bean
    fun springShopOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("VitaPack-Java Demo")
                    .description("VitaPack(Java) Sample")
                    .version("v1.0.0")
                    .license(License().name("비타소프트 웹사이트").url("http://www.vitasoft.co.kr/"))
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("VitaPack-Java Github")
                    .url("https://github.com/vitasoftGit/VitaPack-Java")
            )
    }
}