package com.marsh.toolstash

import com.marsh.toolstash.security.SecurityAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class SecurityApplication

fun main(args: Array<String>) {
    runApplication<SecurityApplication>(*args)
}