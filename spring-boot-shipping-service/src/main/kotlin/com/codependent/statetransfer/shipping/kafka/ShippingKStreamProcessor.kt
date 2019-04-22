package com.codependent.statetransfer.shipping.kafka

import com.codependent.statetransfer.customer.Customer
import com.codependent.statetransfer.order.OrderCreatedEvent
import org.apache.kafka.streams.kstream.KStream
import org.springframework.cloud.stream.annotation.Input

interface ShippingKStreamProcessor {

    @Input("input")
    fun input(): KStream<Int, Customer>

    @Input("order")
    fun order(): KStream<String, OrderCreatedEvent>
/*
    @Output("output")
    fun output(): KStream<String, OrderShippedEvent>*/

}
