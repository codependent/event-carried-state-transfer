package com.codependent.statetransfer.shipping.kafka

import com.codependent.statetransfer.shipping.dto.Customer
import com.codependent.statetransfer.shipping.dto.OrderCreatedEvent
import com.codependent.statetransfer.shipping.dto.OrderShippedEvent
import org.apache.kafka.streams.kstream.KStream
import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output

interface ShippingKStreamProcessor {

    @Input("input")
    fun input(): KStream<Int, Customer>

    @Input("order")
    fun order(): KStream<String, OrderCreatedEvent>

    @Output("output")
    fun output(): KStream<String, OrderShippedEvent>

}
