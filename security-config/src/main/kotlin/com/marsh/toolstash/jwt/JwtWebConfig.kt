package com.marsh.toolstash.jwt

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@AutoConfiguration
@ConditionalOnProperty(
    prefix="marsh.jwt.resolver",
    name=["enabled"],
    havingValue = "true"
)
class JwtWebConfig(
    private val jwtArgumentResolver: JwtArgumentResolver
): WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(jwtArgumentResolver)
    }
}