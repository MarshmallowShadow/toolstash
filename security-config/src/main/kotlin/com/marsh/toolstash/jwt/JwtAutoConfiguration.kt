package com.marsh.toolstash.jwt

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

@ConfigurationProperties("marsh.jwt")
class JwtConfigProperties (
    val secret: String
)

@AutoConfiguration
@EnableConfigurationProperties(JwtConfigProperties::class)
class JwtAutoConfiguration {
    @Bean
    @ConditionalOnProperty("marsh.jwt.secret")
    fun jwtTokenProvider(jwtSecret: String) = JwtTokenProvider(jwtSecret)
}