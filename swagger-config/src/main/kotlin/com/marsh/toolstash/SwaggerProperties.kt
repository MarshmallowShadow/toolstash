package com.marsh.toolstash

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "swagger")
class SwaggerProperties (
    val basePackage: String,
    val title: String,
    val version: String,
    val description: String,
    val termsOfServiceUrl: String,
    val contactName: String,
    val contactUrl: String,
    val email: String,
)