package com.marsh.mail.util

import com.marsh.mail.dto.MailInfoDto
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import org.apache.commons.io.IOUtils
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.FileSystemResource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.io.File
import java.io.FileOutputStream

@Component
class MailUtil(
    private val htmlTemplateEngine: TemplateEngine,
    private val javaMailSender: JavaMailSender
) {
    /**
     *  @description Basic Email Send Logic
     *  @return 업로드된 파일 경로
     */
    fun sendEmail(
        mailInfoDto: MailInfoDto,
        text: String,
    ) {
        send(mailInfoDto.sender, mailInfoDto.recipient, mailInfoDto.subject, mailInfoDto.attachment, mailInfoDto.logoPath, text)
    }


    fun sendEmailThymeHtmlTemplate(
        mailInfoDto: MailInfoDto,
        baseUrl: String,
        htmlPath: String,
    ) {
        val context = Context()
        context.setVariable("baseUrl", baseUrl)
        val text = htmlTemplateEngine.process(htmlPath, context)

        send(mailInfoDto.sender, mailInfoDto.recipient, mailInfoDto.subject, mailInfoDto.attachment, mailInfoDto.logoPath, text)
    }


    private fun send(
        sender: String,
        recipient: String,
        subject: String,
        attachment: List<MultipartFile>?,
        logoPath: String?,
        text: String
    ) {

        val mimeMessage: MimeMessage = javaMailSender.createMimeMessage()
        val message = MimeMessageHelper(mimeMessage, true, "utf-8")

        val logo = File.createTempFile("logo", ".png")
        logoPath?.let {
            val inputStream = FileSystemResource(ClassPathResource(logoPath).file).inputStream
            val outputStream = FileOutputStream(logo)
            IOUtils.copy(inputStream, outputStream)
        }

        attachment?.forEach { mFile ->
            message.addAttachment(mFile.originalFilename!!, mFile)
        }
        message.setFrom(InternetAddress(sender))
        message.setTo(recipient)
        message.setSubject(subject)
        message.setText(text, true)
        if(logoPath != null) message.addInline("logo", logo)

        javaMailSender.send(mimeMessage)
    }
}