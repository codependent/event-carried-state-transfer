package com.codependent.statetransfer.shipping.kafka

import com.codependent.statetransfer.shipping.dto.Customer
import com.codependent.statetransfer.shipping.dto.OrderCreatedEvent
import com.codependent.statetransfer.shipping.dto.OrderEvent
import com.codependent.statetransfer.shipping.dto.OrderShippedEvent
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.utils.Bytes
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.kstream.*
import org.apache.kafka.streams.state.KeyValueStore
import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.serializer.JsonSerde
import org.springframework.messaging.handler.annotation.SendTo


@Suppress("UNCHECKED_CAST")
@Configuration
class ShippingKStreamConfiguration {


    @StreamListener
    @SendTo("output")
    fun process(@Input("input") input: KStream<Int, Customer>, @Input("order") orderEvent: KStream<Int, OrderEvent>): KStream<Int, OrderShippedEvent> {

        val intSerde = Serdes.IntegerSerde()
        val customerSerde = JsonSerde<Customer>(Customer::class.java)
        val orderCreatedSerde = JsonSerde<OrderCreatedEvent>(OrderCreatedEvent::class.java)
        val orderShippedSerde = JsonSerde<OrderShippedEvent>(OrderShippedEvent::class.java)

        val stateStore: Materialized<Int, Customer, KeyValueStore<Bytes, ByteArray>> =
                Materialized.`as`<Int, Customer, KeyValueStore<Bytes, ByteArray>>("customer-store")
                        .withKeySerde(intSerde)
                        .withValueSerde(customerSerde)

        val customerTable: KTable<Int, Customer> = input.groupByKey(Serialized.with(intSerde, customerSerde))
                .reduce({ _, y -> y }, stateStore)


        return (orderEvent.filter { _, value -> value is OrderCreatedEvent }
                .map { key, value -> KeyValue(key, value as OrderCreatedEvent) }
                .selectKey { _, value -> value.customerId } as KStream<Int, OrderCreatedEvent>)
                .join(customerTable, { orderIt, customer ->
                    OrderShippedEvent(orderIt.id, orderIt.productId, customer.name, customer.address)
                }, Joined.with(intSerde, orderCreatedSerde, customerSerde))
                .selectKey { _, value -> value.id }
                //.to("order", Produced.with(intSerde, orderShippedSerde))
    }

}
