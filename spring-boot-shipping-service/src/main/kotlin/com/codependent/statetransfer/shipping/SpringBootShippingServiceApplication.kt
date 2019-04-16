package com.codependent.statetransfer.shipping

import com.codependent.statetransfer.shipping.kafka.ShippingKStreamProcessor
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding

@EnableBinding(ShippingKStreamProcessor::class)
@SpringBootApplication
class SpringBootShippingServiceApplication

fun main(args: Array<String>) {
    runApplication<SpringBootShippingServiceApplication>(*args)
}
