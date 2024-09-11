package com.marsh.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.DecodingException
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.Date

class JwtTokenProvider(
    private val jwtSecret: String,
) {
    private val secretKey = Keys.hmacShaKeyFor(jwtSecret.toByteArray())

    fun generateToken(username: String, role: String, expireIn: Long): String {
        val now = Date()
        val token = Jwts.builder()
            .claim("username", username)
            .claim("role", role)
            .setIssuedAt(now)
            .setExpiration(Date(expireIn))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()

        return token
    }

    fun resolveToken(request: HttpServletRequest): Claims? {
        var token = request.getHeader("Authorization")
        token = when {
            token == null -> return null
            token.startsWith("Bearer ") -> token.replace("Bearer ", "")
            else -> throw DecodingException("Decoding Error: Bearer Not Found")
        }
        return getClaims(token)
    }

    fun getUsername(token: String) =
        getClaims(token)["username"] as String

    fun getAuthentication(claims: Claims) =
        UsernamePasswordAuthenticationToken(claims["username"], "", getAuthority(claims))

    private fun getClaims(token: String) =
        Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body

    private fun getAuthority(claims: Claims) =
        listOf(SimpleGrantedAuthority(claims.get("role", String::class.java)))
}
