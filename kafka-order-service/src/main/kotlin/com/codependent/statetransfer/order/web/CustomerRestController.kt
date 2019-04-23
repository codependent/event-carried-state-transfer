package com.codependent.statetransfer.order.web

import com.codependent.statetransfer.order.dto.Order
import com.codependent.statetransfer.order.service.OrderService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/orders")
class OrderRestController(private val orderService: OrderService) {

    @GetMapping
    fun getAll(): Flux<Order> {
        return orderService.getAll()
    }

    @PostMapping
    fun save(@RequestBody order: Order) {
        return orderService.save(order)
    }

}
