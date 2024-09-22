package com.marsh.toolstash.jwt

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean

@AutoConfiguration
class JwtAutoConfiguration {
    @Bean
    @ConditionalOnProperty("jwtSecret")
    fun jwtTokenProvider(jwtSecret: String) = JwtTokenProvider(jwtSecret)
}