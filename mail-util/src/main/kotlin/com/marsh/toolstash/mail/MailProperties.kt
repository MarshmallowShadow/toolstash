package com.marsh.toolstash.mail

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("marsh.mail")
data class MailProperties (
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
    val smtpAuth: Boolean = true,
    val starttlsEnable: Boolean = true
)