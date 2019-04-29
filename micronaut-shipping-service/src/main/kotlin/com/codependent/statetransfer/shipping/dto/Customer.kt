package com.codependent.statetransfer.shipping.dto

data class Customer(var id: Int?, var name: String?, var address: String?) {
    constructor() : this(null, null, null)
}
