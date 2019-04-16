package com.codependent.statetransfer.shipping.service

import com.codependent.statetransfer.shipping.dto.Customer
import com.codependent.statetransfer.shipping.kafka.CustomerKafkaProducer
//import com.codependent.statetransfer.shipping.kafka.CustomerKafkaProducer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import javax.inject.Singleton

@Singleton
class CustomerServiceImpl(private val customerKafkaProducer: CustomerKafkaProducer) : CustomerService {

    private val customers = mutableMapOf(
            1 to Customer(1, "Joe", "Baltic Street"),
            2 to Customer(2, "Anna", "Rutherford Drive"))

    override fun get(id: Int): Mono<Customer> {
        return if (customers[id] != null) {
            Mono.just(customers[id])
        } else {
            Mono.empty()
        }
    }

    override fun getAll(): Flux<Customer> {
        return customers.values.toFlux()
    }

    override fun save(customer: Customer) {
        customers[customer.id] = customer
        customerKafkaProducer.sendCustomer(customer.id, customer)
    }
}
