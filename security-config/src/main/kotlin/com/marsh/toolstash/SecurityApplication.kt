package com.marsh.toolstash

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SecurityApplication

fun main(args: Array<String>) {
    runApplication<SecurityApplication>(*args)
}