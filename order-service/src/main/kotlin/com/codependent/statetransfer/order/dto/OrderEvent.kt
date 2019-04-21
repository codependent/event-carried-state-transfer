package com.codependent.statetransfer.order.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(value = [
    JsonSubTypes.Type(value = OrderCreatedEvent::class, name = "orderCreated")
])
abstract class OrderEvent
