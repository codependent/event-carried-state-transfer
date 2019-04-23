package com.codependent.statetransfer.customer.web

import com.codependent.statetransfer.customer.Customer
import com.codependent.statetransfer.customer.service.CustomerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerRestController(private val customerService: CustomerService) {

    @PostMapping
    fun save(@RequestBody customer: Customer) {
        customerService.save(customer)
    }

}
