package com.marsh.demo.controller

import com.marsh.demo.request.MailRequest
import com.marsh.demo.service.MailService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.multipart.MultipartFile

@Controller
@RequestMapping("/mail")
class MailController(
    private val mailService: MailService
) {

    @Operation(
        summary="이메일 테스트",
        description = "이메일 보내기"
    )
    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    fun sendMailTest(
        @RequestBody request: MailRequest,
        @RequestPart attachment: List<MultipartFile>?,
    ) {
        mailService.send(
            request.text,
            request.sender,
            request.recipient,
            request.subject,
            attachment,
        )
    }
}