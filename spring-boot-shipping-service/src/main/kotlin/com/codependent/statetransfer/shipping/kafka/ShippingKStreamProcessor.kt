package com.codependent.statetransfer.shipping.kafka

import com.codependent.statetransfer.shipping.dto.Customer
import org.apache.kafka.streams.kstream.KStream
import org.springframework.cloud.stream.annotation.Input

interface ShippingKStreamProcessor {

    @Input("input")
    fun input(): KStream<String, String>

}
