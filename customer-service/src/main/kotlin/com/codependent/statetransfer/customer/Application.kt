package com.codependent.statetransfer.customer

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.codependent.statetransfer.customer")
                .mainClass(Application.javaClass)
                .start()
    }
}
