package com.marsh.toolstash.thymeleaf

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("marsh.thymeleaf")
data class ThymeleafProperties (
    val enabled: Boolean = true,
    val prefix: String?,
    val suffix: String?,
    val isCacheable: Boolean = false,
)