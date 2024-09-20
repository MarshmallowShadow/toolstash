package com.marsh.toolstash.mail

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.Properties

@ConfigurationProperties("marsh.mail")
data class MailConfigProperties (
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
    val smtpAuth: Boolean = true,
    val starttlsEnable: Boolean = true
)