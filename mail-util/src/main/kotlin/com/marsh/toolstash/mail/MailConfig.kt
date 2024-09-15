package com.marsh.toolstash.mail

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
@EnableConfigurationProperties(MailConfigProperties::class)
class MailConfig(
    private val mailConfigProperties: MailConfigProperties
){
    @Bean
    fun javaMailSender(): JavaMailSender {
        val javaMailSender = JavaMailSenderImpl()
        javaMailSender.host = mailConfigProperties.host
        javaMailSender.port = mailConfigProperties.port
        javaMailSender.username = mailConfigProperties.username
        javaMailSender.password = mailConfigProperties.password
        javaMailSender.javaMailProperties = mailConfigProperties.properties

        return javaMailSender
    }
}