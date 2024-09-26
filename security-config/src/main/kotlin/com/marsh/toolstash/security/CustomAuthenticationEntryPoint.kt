package com.marsh.toolstash.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.marsh.toolstash.exception.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class CustomAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        response?.status = HttpStatus.UNAUTHORIZED.value()
        response?.contentType = MediaType.APPLICATION_JSON_VALUE
        response?.writer?.write(objectMapper.writeValueAsString(
            ErrorResponse(
                httpStatus = HttpStatus.UNAUTHORIZED,
                errorCode = "UNAUTHORIZED",
                message = "접속 인증 실패하였습니다."
            )
        ))
    }
}