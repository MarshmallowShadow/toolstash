package com.marsh.toolstash.security.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.marsh.toolstash.security.jwt.ErrorResponse
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

        val errorResponse = objectMapper.writeValueAsString(
            ErrorResponse(
                httpStatus = HttpStatus.UNAUTHORIZED,
                errorCode = "UNAUTHORIZED",
                message = "로그인 후 이용 가능합니다."
            )
        )
        response?.writer?.write(errorResponse)
    }
}