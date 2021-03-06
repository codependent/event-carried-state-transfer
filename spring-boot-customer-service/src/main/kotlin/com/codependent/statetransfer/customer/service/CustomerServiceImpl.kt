package com.codependent.statetransfer.customer.service

import com.codependent.statetransfer.customer.dto.Customer
import org.springframework.cloud.stream.messaging.Source
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux

@Service
class CustomerServiceImpl(private val customerKafkaProducer: Source) : CustomerService {

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

            val message = MessageBuilder.withPayload(customer).setHeader(KafkaHeaders.MESSAGE_KEY, customer.id).build()
            customerKafkaProducer.output().send(message)
        }
    }
}
