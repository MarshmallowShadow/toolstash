package com.marsh.toolstash.mail

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.Properties

@ConfigurationProperties(prefix = "marsh.mail")
data class MailConfigProperties (
    var host: String,
    var port: Int,
    var username: String,
    var password: String,
    var properties: Properties?
)