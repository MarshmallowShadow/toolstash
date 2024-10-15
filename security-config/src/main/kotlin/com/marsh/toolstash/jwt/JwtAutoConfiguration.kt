package com.marsh.toolstash.jwt

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

@AutoConfiguration
@EnableConfigurationProperties(JwtConfigProperties::class)
class JwtAutoConfiguration(
    private val jwtConfigProperties: JwtConfigProperties
) {
    @Bean
    @ConditionalOnProperty("marsh.jwt.secret")
    fun jwtTokenProvider() = JwtTokenProvider(jwtConfigProperties.secret!!)
    
    @Bean
    @ConditionalOnProperty(
        name = ["marsh.jwt.resolver.enabled"],
        havingValue = "true"
    )
    fun jwtArgumentResolver() = JwtArgumentResolver()
}