package com.codependent.statetransfer.shipping.dto

data class Order(var id: Int?, var productId: Int?, var customerId: Int?) {
    constructor() : this(null, null, null)
}
