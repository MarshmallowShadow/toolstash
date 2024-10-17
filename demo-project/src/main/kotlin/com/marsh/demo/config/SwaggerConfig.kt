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
                Info().title("Toolstash Demo")
                    .description("Kotlin-Based Multi-Module Project Demo")
                    .version("v1.0.0")
                    .license(License().name("MarshmallowShadow").url("http://www.github.com/MarshmallowShadow"))
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("Toolstash Github")
                    .url("https://github.com/MarshmallowShadow/toolstash")
            )
    }
}