package com.marsh.toolstash.mail

import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import org.apache.commons.io.IOUtils
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.FileSystemResource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.io.File
import java.io.FileOutputStream

@Component
@ConditionalOnBean(JavaMailSender::class)
class MailUtil(
    private val javaMailSender: JavaMailSender,
    private val htmlTemplateEngine: TemplateEngine,
) {
    /**
     *  
     *  @description Basic logic for sending an email
     *  @author Bo Seung Choi
     *  
     */
    fun sendEmail(
        mailInfoDto: MailInfoDto,
        text: String
    ) {

        val mimeMessage: MimeMessage = javaMailSender.createMimeMessage()
        val message = MimeMessageHelper(mimeMessage, true, "utf-8")

        val logo = File.createTempFile("logo", ".png")
        mailInfoDto.logoPath?.let {
            val inputStream = FileSystemResource(ClassPathResource(mailInfoDto.logoPath).file).inputStream
            val outputStream = FileOutputStream(logo)
            IOUtils.copy(inputStream, outputStream)
        }

        mailInfoDto.attachment?.forEach { mFile ->
            message.addAttachment(mFile.originalFilename!!, mFile)
        }
        message.setFrom(InternetAddress(mailInfoDto.sender))
        message.setTo(mailInfoDto.recipient)
        message.setSubject(mailInfoDto.subject)
        message.setText(text, true)
        if(mailInfoDto.logoPath != null) message.addInline("logo", logo)

        javaMailSender.send(mimeMessage)
    }

    /**
     *  
     *  @description Logic for sending an email using ThymeLeaf Template
     *  @author Bo Seung Choi
     *  
     */
    fun sendEmailThymeHtmlTemplate(
        mailInfoDto: MailInfoDto,
        htmlPath: String,
        context: Context = Context()
    ) {
        val text = htmlTemplateEngine.process(htmlPath, context)

        sendEmail(mailInfoDto, text)
    }
}