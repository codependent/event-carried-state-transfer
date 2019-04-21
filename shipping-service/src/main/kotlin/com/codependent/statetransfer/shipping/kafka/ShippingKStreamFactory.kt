package com.codependent.statetransfer.shipping.kafka

import com.codependent.statetransfer.shipping.dto.Customer
import com.codependent.statetransfer.shipping.dto.OrderCreatedEvent
import com.codependent.statetransfer.shipping.dto.OrderEvent
import com.codependent.statetransfer.shipping.dto.OrderShippedEvent
import io.micronaut.configuration.kafka.serde.JsonSerde
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder
import io.micronaut.context.annotation.Factory
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.utils.Bytes
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.kstream.*
import org.apache.kafka.streams.state.KeyValueStore
import javax.inject.Named
import javax.inject.Singleton


@Suppress("UNCHECKED_CAST")
@Factory
class ShippingKStreamFactory {

    @Singleton
    @Named("customerStream")
    fun customerStream(builder: ConfiguredStreamBuilder): KStream<Int, OrderShippedEvent> {
        val props = builder.configuration
        //props[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.Integer().javaClass.name
        //props[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String().javaClass.name
        //props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"

        val intSerde = Serdes.IntegerSerde()
        val customerSerde = JsonSerde<Customer>(Customer::class.java)
        val orderEventSerde = JsonSerde<OrderEvent>(OrderEvent::class.java)
        val orderCreatedSerde = JsonSerde<OrderCreatedEvent>(OrderCreatedEvent::class.java)
        val orderShippedSerde = JsonSerde<OrderShippedEvent>(OrderShippedEvent::class.java)

        val customerStream = builder.stream<Int, Customer>("customer", Consumed.with(intSerde, customerSerde)) as KStream<Int, Customer>
        val orderEventStream = builder.stream<Int, OrderEvent>("order", Consumed.with(intSerde, orderEventSerde)) as KStream<Int, OrderEvent>

        val customerStateStore: Materialized<Int, Customer, KeyValueStore<Bytes, ByteArray>> =
                Materialized.`as`<Int, Customer, KeyValueStore<Bytes, ByteArray>>("customer-store")
                        .withKeySerde(intSerde)
                        .withValueSerde(customerSerde)

        val customerTable: KTable<Int, Customer> = customerStream.groupByKey(Serialized.with(intSerde, customerSerde))
                .reduce({ _, y -> y }, customerStateStore)

        val orderShipmentStream = (orderEventStream.filter { _, value -> value is OrderCreatedEvent }
                .map { key, value -> KeyValue(key, value as OrderCreatedEvent) }
                .selectKey { _, value -> value.customerId } as KStream<Int, OrderCreatedEvent>)
                .join(customerTable, { orderIt, customer ->
                    OrderShippedEvent(orderIt.id, orderIt.productId, customer.name, customer.address)
                }, Joined.with(intSerde, orderCreatedSerde, customerSerde))
                .selectKey { _, value -> value.id }

        orderShipmentStream.to("order", Produced.with(intSerde, orderShippedSerde))

        return orderShipmentStream as KStream<Int, OrderShippedEvent>
    }
}
