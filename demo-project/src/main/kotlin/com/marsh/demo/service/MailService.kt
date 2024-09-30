package com.marsh.demo.service

import org.springframework.web.multipart.MultipartFile

interface MailService {
    fun send(
        text: String,
        sender: String,
        recipient: String,
        subject: String,
        attachment: List<MultipartFile>?,
    )
}