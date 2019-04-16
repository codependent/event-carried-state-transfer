package com.codependent.statetransfer.shipping.kafka

import com.codependent.statetransfer.shipping.dto.Customer
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.utils.Bytes
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.kstream.Serialized
import org.apache.kafka.streams.state.KeyValueStore
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.serializer.JsonSerde


@Configuration
class ShippingKStreamConfiguration {
    @StreamListener("input")
    fun process(input: KStream<String, Customer>) {

        val stringSerde = Serdes.StringSerde()
        val customerSerde = JsonSerde<Customer>(Customer::class.java)

        val stateStore = Materialized.`as`<String, Customer, KeyValueStore<Bytes, ByteArray>>("customer-store")
                .withKeySerde(stringSerde)
                .withValueSerde(customerSerde)


        input.groupByKey(Serialized.with(null, customerSerde))
                .reduce({ _, y -> y }, stateStore)
    }

}
