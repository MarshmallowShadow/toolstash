package com.marsh.toolstash

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class MailApplication

fun main(args: Array<String>) {
    runApplication<MailApplication>(*args)
}
