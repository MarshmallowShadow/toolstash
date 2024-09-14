package com.marsh.toolstash

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FileApplication

fun main(args: Array<String>) {
    runApplication<FileApplication>(*args)
}
