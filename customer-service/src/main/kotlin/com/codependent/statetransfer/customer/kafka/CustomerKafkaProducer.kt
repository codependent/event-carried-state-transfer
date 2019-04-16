package com.codependent.statetransfer.customer.kafka

import com.codependent.statetransfer.customer.dto.Customer
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic


@KafkaClient
interface CustomerKafkaProducer {

    @Topic("customer")
    fun sendCustomer(@KafkaKey id: Int, customer: Customer)

}
