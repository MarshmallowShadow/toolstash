package com.marsh.toolstash.security

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@ConfigurationProperties("marsh.security.cors.default")
data class CorsWebConfigProperties(
    val enabled: Boolean = false
)

@AutoConfiguration
@EnableConfigurationProperties(CorsWebConfigProperties::class)
@ConditionalOnProperty(
    name=["marsh.security.cors.default.enabled"],
    havingValue = "true"
)
class CorsWebConfig: WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
    }
}
