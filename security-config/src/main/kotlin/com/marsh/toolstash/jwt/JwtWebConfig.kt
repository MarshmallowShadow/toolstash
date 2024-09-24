package com.marsh.toolstash.jwt

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@ConfigurationProperties("marsh.jwt.resolver")
data class JwtWebConfigProperties(
    val enabled: Boolean = false
)


@AutoConfiguration
@EnableConfigurationProperties(JwtWebConfigProperties::class)
@ConditionalOnProperty(
    name=["marsh.jwt.resolver.enabled"],
    havingValue = "true"
)
class JwtWebConfig(
    private val jwtArgumentResolver: JwtArgumentResolver
): WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(jwtArgumentResolver)
    }
}