package com.marsh.toolstash.security.jwt

enum class JwtErrorCode {
    INVALID_SIGNATURE,
    MALFORMED,
    CANNOT_DECODE,
    EXPIRED,
    CANNOT_REFRESH
}
