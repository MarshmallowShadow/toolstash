package com.marsh.demo.service

import org.springframework.web.multipart.MultipartFile

interface CommonsService {
    fun encrypt (
        text: String,
        key: String,
        salt: String,
        splitKey: String,
    ): Map<String, Any>
    fun decrypt(
        text: String,
        key: String,
        salt: String,
        splitKey: String,
    ): Map<String, Any>
    
    fun upload(file: MultipartFile)
}