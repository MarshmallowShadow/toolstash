package com.marsh.demo.service.impl

import com.marsh.demo.service.CommonsService
import com.marsh.toolstash.encoding.EncUtil
import com.marsh.toolstash.file.FileUtil
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class CommonsServiceImpl: CommonsService {
    override fun encrypt (
        text: String,
        key: String,
        salt: String,
        splitKey: String,
    ): Map<String, Any> {
        val map = HashMap<String, Any>()

        map["SHA256"] = EncUtil.sha256Encrypt(text)
        map["MD5"] = EncUtil.md5Encrypt(text, salt, splitKey)
        map["AES256"] = EncUtil.aes256Encrypt(text, key, salt, splitKey)

        return map
    }

    override fun decrypt(
        text: String,
        key: String,
        salt: String,
        splitKey: String,
    ): Map<String, Any> {
        val map = HashMap<String, Any>()

        map["decrypted"] = EncUtil.aes256Decrypt(text, key, salt, splitKey)

        return map
    }

    override fun upload(file: MultipartFile) {
        FileUtil.uploadFile("C://uploads", file)
    }
}