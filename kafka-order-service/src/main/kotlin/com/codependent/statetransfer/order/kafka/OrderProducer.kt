package com.codependent.statetransfer.order.kafka

import com.codependent.statetransfer.order.OrderCreatedEvent
import com.codependent.statetransfer.order.dto.Order
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig
import io.confluent.kafka.serializers.KafkaAvroSerializer
import io.confluent.kafka.serializers.subject.TopicRecordNameStrategy
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.IntegerSerializer
import org.springframework.stereotype.Component
import java.util.*


@Component
class OrderProducer {

    private val producer: KafkaProducer<Int, OrderCreatedEvent>

    init {
        val props = Properties()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        props[ProducerConfig.CLIENT_ID_CONFIG] = "kafka-order-producer"
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = IntegerSerializer::class.java.name
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = KafkaAvroSerializer::class.java.name
        props[AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG] = "http://localhost:8081"
        props["value.subject.name.strategy"] = TopicRecordNameStrategy::class.java.name
        producer = KafkaProducer(props)
    }


    fun sendOrderCreatedEvent(order: Order) {
        val record: ProducerRecord<Int, OrderCreatedEvent> = ProducerRecord("order", order.id, OrderCreatedEvent(order.id, order.productId, order.customerId))
        producer.send(record)
    }
}
