package com.codependent.statetransfer.shipping.dto

data class OrderShipped(var id: Int?, var customerName: String?, var customerAddress: String?) {
    constructor() : this(null, null, null)
}
