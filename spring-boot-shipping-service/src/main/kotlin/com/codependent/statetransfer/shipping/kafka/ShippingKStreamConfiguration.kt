package com.codependent.statetransfer.shipping.kafka

import com.codependent.statetransfer.shipping.dto.Customer
import com.codependent.statetransfer.shipping.dto.Order
import com.codependent.statetransfer.shipping.dto.OrderShipped
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.utils.Bytes
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
    fun process(@Input("input") input: KStream<Int, Customer>, @Input("order") order: KStream<Int, Order>): KStream<Int, OrderShipped> {

        val intSerde = Serdes.IntegerSerde()
        val customerSerde = JsonSerde<Customer>(Customer::class.java)
        val orderSerde = JsonSerde<Order>(Order::class.java)

        val stateStore: Materialized<Int, Customer, KeyValueStore<Bytes, ByteArray>> =
                Materialized.`as`<Int, Customer, KeyValueStore<Bytes, ByteArray>>("customer-store")
                        .withKeySerde(intSerde)
                        .withValueSerde(customerSerde)

        val customerTable: KTable<Int, Customer> = input.groupByKey(Serialized.with(intSerde, customerSerde))
                .reduce({ _, y -> y }, stateStore)

        return (order.selectKey { key, value -> value.customerId } as KStream<Int, Order>)
                .join(customerTable, { orderIt, customer ->
                    OrderShipped(orderIt.id, customer.name, customer.address)
                }, Joined.with(intSerde, orderSerde, customerSerde))

    }

}
