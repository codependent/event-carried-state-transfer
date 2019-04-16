package com.codependent.statetransfer.shipping

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.codependent.statetransfer.shipping")
                .mainClass(Application.javaClass)
                .start()
    }
}
