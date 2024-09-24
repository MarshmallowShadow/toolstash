package com.marsh.toolstash.jwt

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

@ConfigurationProperties("marsh.jwt")
class JwtConfigProperties (
    val enabled: Boolean = false,
    val secret: String = "sampleSecretKeyDONOTUSEFORPRODUCTION"
)

@AutoConfiguration
@EnableConfigurationProperties(JwtConfigProperties::class)
@ConditionalOnProperty(
    name = ["marsh.jwt.enabled"],
    havingValue = "true"
)
class JwtAutoConfiguration(
    private val jwtConfigProperties: JwtConfigProperties
) {
    @Bean
    fun jwtTokenProvider() = JwtTokenProvider(jwtConfigProperties.secret)
}