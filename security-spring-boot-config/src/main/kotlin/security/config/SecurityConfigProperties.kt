package com.marsh.security.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.http.HttpMethod

@ConfigurationProperties(prefix = "security")
class SecurityConfigProperties (
    val authorizeList: Array<AuthorizeProperties>?,
    val ignoreList: Array<String>?
) {
    class AuthorizeProperties (
        val httpMethod: String?,
        val pattern: String,
        val role: Array<String>,
    )
}