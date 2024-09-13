package com.marsh.security.jwt

import org.springframework.http.HttpStatus

open class JwtException(
    val status: HttpStatus,
    errorCode: JwtErrorCode,
    message: String
) : RuntimeException(message) {
    val errorCode = String.format("JWT-%03d", errorCode.ordinal + 1)
}
