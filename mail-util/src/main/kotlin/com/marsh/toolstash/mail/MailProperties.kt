package com.marsh.toolstash.mail

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("marsh.mail")
data class MailProperties (
    val enabled: Boolean = false,
    val host: String?,
    val port: Int = 0,
    val username: String?,
    val password: String?,
    val smtpAuth: Boolean = true,
    val starttlsEnable: Boolean = true
)