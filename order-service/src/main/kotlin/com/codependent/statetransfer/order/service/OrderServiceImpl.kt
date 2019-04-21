package com.codependent.statetransfer.order.service

import com.codependent.statetransfer.order.dto.Order
import com.codependent.statetransfer.order.dto.OrderCreatedEvent
import com.codependent.statetransfer.order.kafka.OrderKafkaProducer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import javax.inject.Singleton

@Singleton
class OrderServiceImpl(private val orderKafkaProducer: OrderKafkaProducer) : OrderService {

    private val orders = mutableMapOf(
            1 to Order(1, 1, 1),
            2 to Order(2, 2, 2))

    override fun get(id: Int): Mono<Order> {
        return if (orders[id] != null) {
            Mono.just(orders[id])
        } else {
            Mono.empty()
        }
    }

    override fun getAll(): Flux<Order> {
        return orders.values.toFlux()
    }

    override fun save(order: Order) {
        orders[order.id] = order
        orderKafkaProducer.sendOrder(order.id,
                OrderCreatedEvent(order.id, order.productId, order.customerId))
    }
}
