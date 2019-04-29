package com.codependent.statetransfer.shipping.dto

data class OrderShippedEvent(var id: Int?, var productId: Int?, var customerName: String?, var customerAddress: String?) : OrderEvent () {
    constructor() : this(null, null, null, null)
}
