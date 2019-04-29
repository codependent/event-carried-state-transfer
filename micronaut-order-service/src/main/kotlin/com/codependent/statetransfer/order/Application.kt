package com.codependent.statetransfer.order

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.codependent.statetransfer.order")
                .mainClass(Application.javaClass)
                .start()
    }
}
