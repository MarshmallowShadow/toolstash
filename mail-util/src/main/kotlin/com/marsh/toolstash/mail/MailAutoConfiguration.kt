package com.marsh.toolstash.mail

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.thymeleaf.TemplateEngine
import java.util.Properties

@AutoConfiguration
@EnableConfigurationProperties(MailConfigProperties::class)
class MailAutoConfiguration(
    private val mailConfigProperties: MailConfigProperties
){
    @Bean
    @ConditionalOnProperty(
        prefix = "marsh.mail",
        name = ["host", "port", "username", "password"]
    )
    fun javaMailSender(): JavaMailSender {
        val props = Properties()
        props.setProperty("mail.smtp.auth", mailConfigProperties.smtpAuth.toString())
        props.setProperty("mail.smtp.starttls.enable", mailConfigProperties.starttlsEnable.toString())
        
        val javaMailSender = JavaMailSenderImpl()
        javaMailSender.host = mailConfigProperties.host
        javaMailSender.port = mailConfigProperties.port
        javaMailSender.username = mailConfigProperties.username
        javaMailSender.password = mailConfigProperties.password
        javaMailSender.javaMailProperties = props
        
        return javaMailSender
    }
    
    @Bean
    @ConditionalOnBean(JavaMailSender::class)
    fun mailUtil(
        javaMailSender: JavaMailSender,
        htmlTemplateEngine: TemplateEngine
    ): MailUtil {
        return MailUtil(javaMailSender, htmlTemplateEngine)
    }
}