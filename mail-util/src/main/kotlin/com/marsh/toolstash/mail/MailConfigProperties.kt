package com.marsh.toolstash.mail

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.stereotype.Component
import java.util.Properties

@ConfigurationProperties(prefix = "mail")
data class MailConfigProperties @ConstructorBinding constructor (
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
    val properties: Properties
)