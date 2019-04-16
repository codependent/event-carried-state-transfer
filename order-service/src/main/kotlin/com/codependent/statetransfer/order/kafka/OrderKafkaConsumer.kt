package com.codependent.statetransfer.order.kafka

import com.codependent.statetransfer.order.dto.Order
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic


@KafkaListener(offsetReset = OffsetReset.EARLIEST)
class OrderKafkaConsumer {

    @Topic("order")
    fun receive(@KafkaKey id: Int, order: Order) {
        println("Got Order - id  $id value $order")
    }

}
