package com.marsh.toolstash.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("marsh.jwt")
data class JwtConfigProperties (
    val secret: String?,
    
    val resolver: Map<String, Any>?
)