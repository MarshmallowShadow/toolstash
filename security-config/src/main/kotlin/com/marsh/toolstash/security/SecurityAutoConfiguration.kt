package com.marsh.toolstash.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.marsh.toolstash.jwt.JwtAuthenticationFilter
import com.marsh.toolstash.jwt.JwtTokenProvider
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@AutoConfiguration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityConfigProperties::class)
@ConditionalOnBean(JwtTokenProvider::class)
@ConditionalOnProperty(
    name = ["marsh.security.enabled"],
    havingValue = "true"
)
class SecurityAutoConfiguration(
    private val objectMapper: ObjectMapper,
    private val jwtTokenProvider: JwtTokenProvider,
    private val configProperties: SecurityConfigProperties
) {

    @Bean
    @ConditionalOnProperty("marsh.security.authorizeList")
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            httpBasic { disable() }
            csrf { disable() }
            cors { }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
            authorizeRequests {
                configProperties.authorizeList?.forEach { 
                    if(it.httpMethod != null)
                        authorize(
                            HttpMethod.valueOf(it.httpMethod),
                            it.pattern,
                            if(!it.role.contains("ALL")) permitAll else hasAnyRole(*it.role)
                        )
                    else
                        authorize(
                            it.pattern,
                            if(!it.role.contains("ALL")) permitAll else hasAnyRole(*it.role)
                        )
                }
            }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(
                JwtAuthenticationFilter(objectMapper, jwtTokenProvider)
            )
            exceptionHandling { authenticationEntryPoint = CustomAuthenticationEntryPoint(objectMapper) }
        }
        return http.build()
    }

    @Bean
    @ConditionalOnProperty("marsh.security.ignoreList")
    fun ignoringCustomizer() =
        WebSecurityCustomizer { web: WebSecurity ->
            configProperties.ignoreList?.let {
                web.ignoring().requestMatchers(
                    *it
                )
            }
        }
}
