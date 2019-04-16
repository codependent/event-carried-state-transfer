package com.codependent.statetransfer.shipping.kafka

import com.codependent.statetransfer.shipping.dto.Customer
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder
import io.micronaut.context.annotation.Factory
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.Materialized
import javax.inject.Named
import javax.inject.Singleton


//@Factory
class ShippingKStreamFactory {

    @Singleton
    @Named("customerStream")
    fun wordCountStream(builder: ConfiguredStreamBuilder): KStream<Int, Customer> {
        val props = builder.configuration
        props[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String().javaClass.name
        props[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String().javaClass.name
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"

        val source = builder.stream<Int, Customer>("customer2")
        source.groupByKey().reduce({ _, y -> y }, Materialized.`as`("customer2-store"))

        return source
    }
}
