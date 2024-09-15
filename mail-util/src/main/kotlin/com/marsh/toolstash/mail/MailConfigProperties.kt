package com.marsh.toolstash.mail

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.Properties

@ConfigurationProperties(prefix = "mail")
class MailConfigProperties (
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
    val properties: Properties
)