package com.marsh.demo.controller

import com.marsh.demo.service.MainService
import com.marsh.toolstash.dto.UserInfoDto
import com.marsh.toolstash.jwt.JwtResolver
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class MainController(
    private val mainService: MainService,
) {
    
    @GetMapping("/encoding")
    @ResponseStatus(HttpStatus.OK)
    fun encodingTest(@RequestBody request: Map<String, Any>): Map<String, Any> {
        return mainService.encrypt(request["text"].toString())
    }
    
    @GetMapping("/decoding")
    @ResponseStatus(HttpStatus.OK)
    fun decodingTest(@RequestBody request: Map<String, Any>): Map<String, Any> {
        return mainService.decrypt(request["data"].toString())
    }
    
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    fun uploadTest(@RequestPart file: MultipartFile) {
        mainService.upload(file)
    }
    
    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    fun sendMailTest() {
        mainService.sendEmail()
    }

    @GetMapping("/access/test")
    @ResponseStatus(HttpStatus.OK)
    fun accessTest() {}
    
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(): Map<String, Any> {
        return mainService.login()
    }

    @GetMapping("/getLoginInfo")
    @ResponseStatus(HttpStatus.OK)
    fun loginInfo(
        @JwtResolver userInfoDto: UserInfoDto,
    ): UserInfoDto {
        return userInfoDto
    }
}