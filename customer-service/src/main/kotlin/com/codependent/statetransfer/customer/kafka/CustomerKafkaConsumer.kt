package com.codependent.statetransfer.customer.kafka

import com.codependent.statetransfer.customer.dto.Customer
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic


@KafkaListener(offsetReset = OffsetReset.EARLIEST)
class CustomerKafkaConsumer {

    @Topic("customer")
    fun receive(@KafkaKey id: Int, customer: Customer) {
        println("Got Customer - id  $id value $customer")
    }

}
