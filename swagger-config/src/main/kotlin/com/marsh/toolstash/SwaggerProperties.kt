package com.marsh.toolstash

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "swagger")
data class SwaggerProperties (
    var basePackage: String,
    var title: String,
    var version: String,
    var description: String,
    var termsOfServiceUrl: String,
    var contactName: String,
    var contactUrl: String,
    var email: String,
)