package com.codependent.statetransfer.customer.service

import com.codependent.statetransfer.customer.Customer
import com.codependent.statetransfer.customer.kafka.CustomerProducer
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux

@Service
class CustomerServiceImpl(private val customerProducer: CustomerProducer) : CustomerService {

    private val customers = mutableMapOf(
            1 to Customer(1, "Joe", "Baltic Street"),
            2 to Customer(2, "Anna", "Rutherford Drive"))

    override fun get(id: Int): Mono<Customer> {
        return if (customers[id] != null) {
            Mono.just(customers[id] as Customer)
        } else {
            Mono.empty()
        }
    }

    override fun getAll(): Flux<Customer> {
        return customers.values.toFlux()
    }

    override fun save(customer: Customer) {
        if (customer.id != null) {
            customers[customer.id] = customer
            customerProducer.sendCustomerEvent(customer)
        }
    }
}
