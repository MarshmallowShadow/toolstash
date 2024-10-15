package com.marsh.toolstash.mail

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("marsh.mail")
data class MailProperties (
    val enabled: Boolean?,
    val host: String?,
    val port: Int?,
    val username: String?,
    val password: String?,
    val smtpAuth: Boolean?,
    val starttlsEnable: Boolean?
)