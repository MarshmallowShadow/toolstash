package com.marsh.toolstash.aspectj

import mu.KLogger
import mu.KotlinLogging
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Bean

@AutoConfiguration
class AspectjAutoConfiguration {
    @Bean
    fun logger() = KotlinLogging.logger {}
    
    @Bean
    @ConditionalOnBean(KLogger::class)
    fun aopConfig(): AopConfig = AopConfig(logger())
}