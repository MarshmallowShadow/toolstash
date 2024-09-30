package com.marsh.demo.controller

import com.marsh.demo.request.EncodingRequest
import com.marsh.demo.service.CommonsService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class CommonsController(
    private val commonsService: CommonsService
) {
    @Operation(
        summary="인코딩 테스트",
        description = "SHA256, MD5, AES256 인코딩 지원"
    )
    @GetMapping("/encoding")
    @ResponseStatus(HttpStatus.OK)
    fun encodingTest(@RequestBody request: EncodingRequest): Map<String, Any> {
        return commonsService.encrypt(
            request.text,
            request.key,
            request.salt,
            request.splitKey
        )
    }

    @Operation(
        summary="디코딩 테스트",
        description = "AES256 디코딩 샘플"
    )
    @GetMapping("/decoding")
    @ResponseStatus(HttpStatus.OK)
    fun decodingTest(@RequestBody request: EncodingRequest): Map<String, Any> {
        return commonsService.decrypt(
            request.text,
            request.key,
            request.salt,
            request.splitKey
        )
    }

    @Operation(
        summary="파일 업로드 테스트",
    )
    @PostMapping("/file/upload")
    @ResponseStatus(HttpStatus.OK)
    fun uploadTest(@RequestPart file: MultipartFile) {
        commonsService.upload(file)
    } 
}