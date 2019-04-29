package com.codependent.statetransfer.shipping.dto

data class OrderCreatedEvent(var id: Int?, var productId: Int?, var customerId: Int?) : OrderEvent() {
    constructor() : this(null, null, null)
}
