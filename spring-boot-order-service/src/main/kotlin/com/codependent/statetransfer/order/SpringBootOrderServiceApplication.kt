package com.codependent.statetransfer.order

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient

@EnableSchemaRegistryClient
@EnableBinding(Source::class)
@SpringBootApplication
class SpringBootOrderServiceApplication

fun main(args: Array<String>) {
    runApplication<SpringBootOrderServiceApplication>(*args)
}
