package com.marsh.toolstash.swagger

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("marsh.swagger")
data class SwaggerProperties (
    val enabled: Boolean = true,
    val basePackage: String?,
    val title: String?,
    val version: String?,
    val description: String?,
    val termsOfServiceUrl: String?,
    val contactName: String?,
    val contactUrl: String?,
    val email: String?,
)