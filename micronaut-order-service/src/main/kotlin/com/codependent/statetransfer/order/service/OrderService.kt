package com.codependent.statetransfer.order.service

import com.codependent.statetransfer.order.dto.Order
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface OrderService {

    fun save(order: Order)
    fun get(id: Int): Mono<Order>
    fun getAll(): Flux<Order>

}
