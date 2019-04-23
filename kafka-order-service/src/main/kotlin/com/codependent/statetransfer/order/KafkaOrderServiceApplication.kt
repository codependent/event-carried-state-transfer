package com.codependent.statetransfer.order

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaCustomerServiceApplication

fun main(args: Array<String>) {
    runApplication<KafkaCustomerServiceApplication>(*args)
}
