package com.marsh.toolstash.security.config

import org.springframework.boot.context.properties.ConfigurationProperties

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