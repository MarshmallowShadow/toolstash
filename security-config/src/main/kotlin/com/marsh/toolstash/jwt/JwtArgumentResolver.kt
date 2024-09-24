package com.marsh.toolstash.jwt

import com.marsh.toolstash.dto.UserInfoDto
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class JwtArgumentResolver: HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter) =
        parameter.getParameterAnnotation(JwtResolver::class.java) != null &&
                parameter.parameterType == UserInfoDto::class.java

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        val authenticationToken = SecurityContextHolder.getContext().authentication
        return UserInfoDto(
            authenticationToken.principal.toString(),
            authenticationToken.authorities.map { it.authority }.first()
        )
    }
}