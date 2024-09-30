package com.marsh.demo.service.impl

import com.marsh.demo.service.MainService
import com.marsh.toolstash.jwt.JwtTokenProvider
import org.springframework.stereotype.Service

@Service
class MainServiceImpl(
    private val jwtTokenProvider: JwtTokenProvider
) : MainService{
    
    override fun login(): Map<String, Any> {
        val accessToken = jwtTokenProvider.generateToken("marsh", "USER", 946080000000)
        val refreshToken = jwtTokenProvider.generateToken("marsh", "USER", 946080000000)
        
        return hashMapOf("accessToken" to accessToken, "refreshToken" to refreshToken)
    }
}