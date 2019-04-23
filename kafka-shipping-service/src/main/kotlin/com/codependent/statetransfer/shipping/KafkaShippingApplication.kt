package com.codependent.statetransfer.shipping

import com.codependent.statetransfer.customer.Customer
import com.codependent.statetransfer.order.OrderCreatedEvent
import com.codependent.statetransfer.order.OrderShippedEvent
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.utils.Bytes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.*
import org.apache.kafka.streams.kstream.Produced
import org.apache.kafka.streams.state.KeyValueStore
import java.util.*
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.KeyValue
import io.confluent.kafka.serializers.subject.TopicRecordNameStrategy




fun main(args: Array<String>) {

    val builder = StreamsBuilder()

    val streamsConfiguration = Properties()
    streamsConfiguration[StreamsConfig.APPLICATION_ID_CONFIG] = "kafka-shipping-service"
    //streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.ByteArray()::class.java.name)
    //streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, SpecificAvroSerde::class.java)
    streamsConfiguration[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = "http://localhost:9092"
    streamsConfiguration[AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG] = "http://localhost:8081"
    streamsConfiguration["value.subject.name.strategy"] = TopicRecordNameStrategy::class.java.name

    val serdeConfig = mapOf(
        AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG to "http://localhost:8081"
    )

    //val byteArraySerde = Serdes.ByteArray()
    val intSerde = Serdes.IntegerSerde()
    val customerSerde = SpecificAvroSerde<Customer>()
    customerSerde.configure(serdeConfig, false)
    val orderCreatedSerde = SpecificAvroSerde<OrderCreatedEvent>()
    orderCreatedSerde.configure(serdeConfig, false)
    val orderShippedSerde = SpecificAvroSerde<OrderShippedEvent>()
    orderShippedSerde.configure(serdeConfig, false)


    val customerStream = builder.stream<Int, Customer>("customer",
        Consumed.with(intSerde, customerSerde)) as KStream<Int, Customer>

    val stateStore: Materialized<Int, Customer, KeyValueStore<Bytes, ByteArray>> =
        Materialized.`as`<Int, Customer, KeyValueStore<Bytes, ByteArray>>("customer-store")
            .withKeySerde(intSerde)
            .withValueSerde(customerSerde)

    val customerTable = customerStream
        .map { key, value -> KeyValue(key, value) }
        .groupByKey(Serialized.with(intSerde, customerSerde))
        .reduce({ _, y -> y }, stateStore)

    val orderStream = builder.stream<Int, OrderCreatedEvent>("order",
        Consumed.with(intSerde, orderCreatedSerde)) as KStream<Int, OrderCreatedEvent>

    (orderStream.filter { _, value -> value is OrderCreatedEvent && value.id != 0 }
        .selectKey { _, value -> value.customerId } as KStream<Int, OrderCreatedEvent>)
        .join(customerTable, { orderIt, customer ->
            OrderShippedEvent(orderIt.id, orderIt.productId, customer.name, customer.address)
        }, Joined.with(intSerde, orderCreatedSerde, customerSerde))
        .selectKey { _, value -> value.id }
        .to("order", Produced.with(intSerde, orderShippedSerde))


    val streams = KafkaStreams(builder.build(), streamsConfiguration)
    streams.start()
}
