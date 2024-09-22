package com.marsh.toolstash.exception

enum class JwtErrorCode {
    INVALID_SIGNATURE,
    MALFORMED,
    CANNOT_DECODE,
    EXPIRED,
    CANNOT_REFRESH
}
