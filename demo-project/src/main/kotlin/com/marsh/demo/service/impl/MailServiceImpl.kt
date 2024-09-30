package com.marsh.demo.service.impl

import com.marsh.demo.service.MailService
import com.marsh.toolstash.mail.MailInfoDto
import com.marsh.toolstash.mail.MailUtil
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class MailServiceImpl(
    private val mailUtil: MailUtil
): MailService {
    override fun send(
        text: String,
        sender: String,
        recipient: String,
        subject: String,
        attachment: List<MultipartFile>?,
    ) {
        val mailInfoDto = MailInfoDto(
            sender, recipient, subject, attachment
        )
        mailUtil.sendEmail(mailInfoDto, text)
    }
}