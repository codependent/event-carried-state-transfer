package com.codependent.statetransfer.order.web

import com.codependent.statetransfer.order.dto.Order
import com.codependent.statetransfer.order.service.OrderService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import reactor.core.publisher.Flux

@Controller("/orders")
class OrderRestController(private val orderService: OrderService) {

    @Get
    fun getAll(): Flux<Order> {
        return orderService.getAll()
    }

    @Post
    fun save(order: Order) {
        return orderService.save(order)
    }

}
