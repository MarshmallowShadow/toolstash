package com.marsh.swagger

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "swagger")
class SwaggerProperties (
    val title: String,
    val version: String,
    val description: String,
    val termsOfServiceUrl: String,
    val name: String,
    val contactUrl: String,
    val email: String,
    val basePackage: String,
)