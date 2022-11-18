package com.somewear.hellospace

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloSpaceApplication

fun main(args: Array<String>) {
	runApplication<HelloSpaceApplication>(*args)
}
