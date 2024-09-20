package com.marsh.toolstash

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "swagger")
data class SwaggerProperties @ConstructorBinding constructor (
    val basePackage: String,
    val title: String,
    val version: String,
    val description: String,
    val termsOfServiceUrl: String,
    val contactName: String,
    val contactUrl: String,
    val email: String,
)