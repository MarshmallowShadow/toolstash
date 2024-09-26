package com.marsh.toolstash.aspectj

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean

@AutoConfiguration
class AspectjAutoConfiguration {
    @Bean
    fun aopConfig(): AopConfig = AopConfig()
}