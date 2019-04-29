package com.codependent.statetransfer.customer.web

import com.codependent.statetransfer.customer.dto.Customer
import com.codependent.statetransfer.customer.service.CustomerService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import reactor.core.publisher.Flux

@Controller("/customers")
class CustomerRestController(private val customerService: CustomerService) {

    @Get
    fun getAll(): Flux<Customer> {
        return customerService.getAll()
    }

    @Post
    fun save(customer: Customer) {
        return customerService.save(customer)
    }

}
