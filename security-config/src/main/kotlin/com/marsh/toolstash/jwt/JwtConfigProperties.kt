package com.marsh.toolstash.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("marsh.jwt")
data class JwtConfigProperties (
    val enabled: Boolean = false,
    val secret: String = "sampleSecretKeyDONOTUSEFORPRODUCTION",
    
    val resolver: JwtWebConfigResolver = JwtWebConfigResolver()
) {
    data class JwtWebConfigResolver (
        val enabled: Boolean = false
    )
}