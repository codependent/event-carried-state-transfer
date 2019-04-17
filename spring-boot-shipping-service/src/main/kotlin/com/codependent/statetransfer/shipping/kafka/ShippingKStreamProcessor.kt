package com.codependent.statetransfer.shipping.kafka

import com.codependent.statetransfer.shipping.dto.Customer
import com.codependent.statetransfer.shipping.dto.Order
import com.codependent.statetransfer.shipping.dto.OrderShipped
import org.apache.kafka.streams.kstream.KStream
import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output

interface ShippingKStreamProcessor {

    @Input("input")
    fun input(): KStream<Int, Customer>

    @Input("order")
    fun order(): KStream<String, Order>

    @Output("output")
    fun output(): KStream<String, OrderShipped>

}
