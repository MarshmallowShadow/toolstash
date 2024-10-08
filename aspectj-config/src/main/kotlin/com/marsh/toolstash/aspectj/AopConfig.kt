package com.marsh.toolstash.aspectj

import mu.KotlinLogging
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class AopConfig {
    private val log = KotlinLogging.logger {}
    
    @Before("execution(* *..controller.*.*(..))")
    fun logRequestURI() {
        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val httpMethod = request.method
        val queryString = request.queryString?.let { "?$it" } ?: ""
        log.info("[$httpMethod] ${request.requestURI}$queryString")
    }

    @AfterThrowing(value = "execution(* *..controller.*.*(..))", throwing = "exception")
    fun logError(exception: RuntimeException) {
        log.error("${exception.message}")
    }
}