package com.marsh.toolstash.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("marsh.jwt")
data class JwtConfigProperties (
    val secret: String = "SampleSecretKey",
    
    val resolver: Map<String, Any> = hashMapOf("enabled" to false)
)