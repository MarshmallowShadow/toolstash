package com.marsh.demo.service.impl

import com.marsh.demo.service.MainService
import com.marsh.toolstash.encoding.EncUtil
import com.marsh.toolstash.file.FileUtil
import com.marsh.toolstash.jwt.JwtTokenProvider
import com.marsh.toolstash.mail.MailInfoDto
import com.marsh.toolstash.mail.MailUtil
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import kotlin.collections.HashMap

@Service
class MainServiceImpl(
    private val mailUtil: MailUtil,
    private val jwtTokenProvider: JwtTokenProvider
) : MainService{
    
    private final val key = "2wK0zHnZAUaWO539hRr7h5R9SX0IMLzN"
    private final val salt = "@marshsample@"
    private final val splitKey = "@::@"
    
    override fun encrypt (text: String): Map<String, Any> {
        val map = HashMap<String, Any>()

        map["SHA256"] = EncUtil.SHA256Encrypt(text)
        map["MD5"] = EncUtil.MD5Encrypt(text, salt, splitKey)
        map["AES256"] = EncUtil.AES256Encrypt(text, key, salt, splitKey)
        
        return map
    }
    
    override fun decrypt(data: String): Map<String, Any> {
        val map = HashMap<String, Any>()
        
        map["decrypted"] = EncUtil.AES256Decrypt(data, key, salt, splitKey)
        
        return map
    }
    
    override fun upload(file: MultipartFile) {
        FileUtil.uploadFile("C://uploads", file)
    }

    override fun sendEmail() {
        mailUtil.sendEmail(
            MailInfoDto(
                attachment = null,
                logoPath = null,
                sender = "brianmc556@gmail.com",
                recipient = "brianmc556@gmail.com",
                subject = "아주 중요한 메세지"
            ),
            "Benis :)"
        )
    }
    
    override fun login(): Map<String, Any> {
        val accessToken = jwtTokenProvider.generateToken("marsh", "USER", 946080000000)
        val refreshToken = jwtTokenProvider.generateToken("marsh", "USER", 946080000000)
        
        return hashMapOf("accessToken" to accessToken, "refreshToken" to refreshToken)
    }
}