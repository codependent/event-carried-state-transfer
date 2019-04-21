package com.codependent.statetransfer.shipping

import com.codependent.statetransfer.shipping.dto.OrderShippedEvent
import com.codependent.statetransfer.shipping.kafka.ShippingKStreamProcessor
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding

@EnableBinding(ShippingKStreamProcessor::class)
@SpringBootApplication
class SpringBootShippingServiceApplication

fun main(args: Array<String>) {

    val objectMapper = ObjectMapper()
    val orderShippedEvent = OrderShippedEvent(1, 1, "a", "b")

    val writeValueAsString = objectMapper.writeValueAsString(orderShippedEvent)
    println(writeValueAsString)

    runApplication<SpringBootShippingServiceApplication>(*args)
}
