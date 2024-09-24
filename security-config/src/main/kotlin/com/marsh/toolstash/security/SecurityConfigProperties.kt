package com.marsh.toolstash.security

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("marsh.security")
data class SecurityConfigProperties (
    val authorizeList: Array<AuthorizeProperties>?,
    val ignoreList: Array<String>?
) {
    class AuthorizeProperties (
        val pattern: String,
        val role: Array<String>,
        val httpMethod: String? = null,
    )
    
    /* Auto-Generated */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SecurityConfigProperties

        if (authorizeList != null) {
            if (other.authorizeList == null) return false
            if (!authorizeList.contentEquals(other.authorizeList)) return false
        } else if (other.authorizeList != null) return false
        if (ignoreList != null) {
            if (other.ignoreList == null) return false
            if (!ignoreList.contentEquals(other.ignoreList)) return false
        } else if (other.ignoreList != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = authorizeList?.contentHashCode() ?: 0
        result = 31 * result + (ignoreList?.contentHashCode() ?: 0)
        return result
    }
}