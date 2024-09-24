package com.marsh.toolstash.mail

import org.springframework.web.multipart.MultipartFile

class MailInfoDto(
    val sender: String,
    val recipient: String,
    val subject: String,
    val attachment: List<MultipartFile>? = null,
    val logoPath: String? = null
)