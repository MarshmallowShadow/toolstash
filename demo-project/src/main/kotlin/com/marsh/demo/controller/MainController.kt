package com.marsh.demo.controller

import com.marsh.demo.service.MainService
import com.marsh.toolstash.dto.UserInfoDto
import com.marsh.toolstash.jwt.JwtResolver
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController(
    private val mainService: MainService,
) {

    @Operation(
        summary="시큐리티 접속 테스트",
        description = ""
    )
    @GetMapping("/access/test")
    @ResponseStatus(HttpStatus.OK)
    fun accessTest() {}

    @Operation(
        summary="로그인 테스트",
        description = "이메일 보내기"
    )
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(): Map<String, Any> {
        return mainService.login()
    }

    @Operation(
        summary="로그인 정보 조회",
        description = "이메일 보내기"
    )
    @GetMapping("/getLoginInfo")
    @ResponseStatus(HttpStatus.OK)
    fun loginInfo(
        @JwtResolver userInfoDto: UserInfoDto,
    ): UserInfoDto {
        return userInfoDto
    }
}