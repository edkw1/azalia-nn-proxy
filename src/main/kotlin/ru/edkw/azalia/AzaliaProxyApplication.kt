package ru.edkw.azalia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AzaliaProxyApplication

fun main(args: Array<String>) {
	runApplication<AzaliaProxyApplication>(*args)
}
