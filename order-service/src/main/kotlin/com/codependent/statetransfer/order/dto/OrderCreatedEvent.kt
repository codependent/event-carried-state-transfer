package com.codependent.statetransfer.order.dto

data class OrderCreatedEvent(var id: Int, var productId: Int, var customerId: Int) : OrderEvent()
