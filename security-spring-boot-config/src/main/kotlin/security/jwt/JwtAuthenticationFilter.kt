package com.marsh.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.io.DecodingException
import io.jsonwebtoken.security.SignatureException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets

class JwtAuthenticationFilter(
    private val objectMapper: ObjectMapper,
    private val jwtTokenProvider: JwtTokenProvider
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val claims = jwtTokenProvider.resolveToken(request)
            if (claims != null) {
                SecurityContextHolder.getContext().authentication = jwtTokenProvider.getAuthentication(claims)
            }
            filterChain.doFilter(request, response)
        } catch (exception: SignatureException) {
            sendErrorMessage(response, JwtException(HttpStatus.UNAUTHORIZED, JwtErrorCode.INVALID_SIGNATURE, "유효하지 않은 토큰입니다."))
        } catch (exception: MalformedJwtException) {
            sendErrorMessage(response, JwtException(HttpStatus.UNAUTHORIZED, JwtErrorCode.MALFORMED, "손상된 토큰입니다."))
        } catch (exception: DecodingException) {
            sendErrorMessage(response, JwtException(HttpStatus.UNAUTHORIZED, JwtErrorCode.CANNOT_DECODE, "잘못된 인증입니다."))
        } catch (exception: ExpiredJwtException) {
            sendErrorMessage(response, JwtException(HttpStatus.UNAUTHORIZED, JwtErrorCode.EXPIRED, "만료된 토큰입니다."))
        }
    }

    private fun sendErrorMessage(response: HttpServletResponse, jwtException: JwtException) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.writer.write(
            objectMapper.writeValueAsString(
                ErrorResponse(
                    jwtException.status,
                    jwtException.errorCode,
                    jwtException.message!!
                )
            )
        )
    }
}
