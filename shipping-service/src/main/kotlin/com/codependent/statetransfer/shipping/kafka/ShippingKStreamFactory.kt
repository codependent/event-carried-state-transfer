package com.codependent.statetransfer.shipping.kafka

import com.codependent.statetransfer.shipping.dto.Customer
import com.codependent.statetransfer.shipping.dto.Order
import com.codependent.statetransfer.shipping.dto.OrderShipped
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.configuration.kafka.serde.JsonSerde
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder
import io.micronaut.context.annotation.Factory
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.utils.Bytes
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.*
import org.apache.kafka.streams.state.KeyValueStore
import javax.inject.Named
import javax.inject.Singleton


@Suppress("UNCHECKED_CAST")
@Factory
class ShippingKStreamFactory {

    @Singleton
    @Named("customerStream")
    fun customerStream(builder: ConfiguredStreamBuilder): KStream<Int, OrderShipped> {
        val props = builder.configuration
        //props[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.Integer().javaClass.name
        //props[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String().javaClass.name
        //props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"

        val objectMapper = ObjectMapper()
        //val readValue = objectMapper.readValue("{\"id\":99,\"name\":\"John\",\"address\":\"Cipress Street\"}".toByteArray(), Customer::class.java)


        val intSerde = Serdes.IntegerSerde()
        val customerSerde = JsonSerde<Customer>(Customer::class.java)
        val orderSerde = JsonSerde<Order>(Order::class.java)
        val orderShippedSerde = JsonSerde<OrderShipped>(OrderShipped::class.java)

        val customerStream = builder.stream<Int, Customer>("customer", Consumed.with(intSerde, customerSerde)) as KStream<Int, Customer>
        val orderStream = builder.stream<Int, Order>("order", Consumed.with(intSerde, orderSerde)) as KStream<Int, Order>

        val customerStateStore: Materialized<Int, Customer, KeyValueStore<Bytes, ByteArray>> =
                Materialized.`as`<Int, Customer, KeyValueStore<Bytes, ByteArray>>("customer-store")
                        .withKeySerde(intSerde)
                        .withValueSerde(customerSerde)

        val customerTable: KTable<Int, Customer> = customerStream.groupByKey(Serialized.with(intSerde, customerSerde))
                .reduce({ _, y -> y }, customerStateStore)

        val orderShipmentStream = (orderStream.selectKey { key, value -> value.customerId } as KStream<Int, Order>)
                .join(customerTable, { orderIt, customer ->
                    OrderShipped(orderIt.id, orderIt.productId, customer.name, customer.address)
                }, Joined.with(intSerde, orderSerde, customerSerde))
                .selectKey { key, value -> value.id }

        orderShipmentStream.to("order", Produced.with(intSerde, orderShippedSerde))

        return orderShipmentStream as KStream<Int, OrderShipped>
    }
}
