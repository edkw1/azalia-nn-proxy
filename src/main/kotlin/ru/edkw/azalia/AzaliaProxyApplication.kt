package ru.edkw.azalia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class AzaliaProxyApplication

fun main(args: Array<String>) {
	runApplication<AzaliaProxyApplication>(*args)
}
