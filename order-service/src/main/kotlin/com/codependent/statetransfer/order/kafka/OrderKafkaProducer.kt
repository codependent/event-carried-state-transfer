package com.codependent.statetransfer.order.kafka

import com.codependent.statetransfer.order.dto.Order
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic


@KafkaClient
interface OrderKafkaProducer {

    @Topic("order")
    fun sendOrder(@KafkaKey id: Int, order: Order)

}
