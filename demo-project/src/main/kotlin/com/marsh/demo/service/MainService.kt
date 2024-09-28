package com.marsh.demo.service

import org.springframework.web.multipart.MultipartFile

interface MainService {
    fun encrypt (text: String): Map<String, Any>
    fun decrypt(data: String): Map<String, Any>

    fun upload(file: MultipartFile)
    
    fun sendEmail()
    
    fun login(): Map<String, Any>
}