package com.codependent.statetransfer.customer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.cloud.stream.messaging.Source

@EnableBinding(Source::class)
@SpringBootApplication
class SpringBootCustomerServiceApplication

fun main(args: Array<String>) {
    runApplication<SpringBootCustomerServiceApplication>(*args)
}

