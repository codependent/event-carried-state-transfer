package com.codependent.statetransfer.shipping.stream

import com.codependent.statetransfer.customer.Customer
import com.codependent.statetransfer.order.OrderCreatedEvent
import com.codependent.statetransfer.order.OrderShippedEvent
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