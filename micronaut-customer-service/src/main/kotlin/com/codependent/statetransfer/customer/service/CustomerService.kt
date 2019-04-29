package com.codependent.statetransfer.customer.service

import com.codependent.statetransfer.customer.dto.Customer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {

    fun save(customer: Customer)
    fun get(id: Int): Mono<Customer>
    fun getAll(): Flux<Customer>

}
