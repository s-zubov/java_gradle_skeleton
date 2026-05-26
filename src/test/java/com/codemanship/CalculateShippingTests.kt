package com.codemanship

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class CalculateShippingTests {

    @Test
    fun `for UK order cost under 100`() {
        val product = Product(id = 123, description = "Musica", price = BigDecimal("99.99"), stockQty = 8, holdQty = 1)
        val order = Order(product, 1)

        val shippingCost = order.shippingCost

        Assertions.assertEquals(BigDecimal("5.99"), shippingCost)
    }

    @Test
    fun `for UK order cost above 100`() {
        val product = Product(
            id = 327,
            description = "Ibanez Tube Screamer",
            price = BigDecimal("100.00"),
            stockQty = 7,
            holdQty = 1
        )
        val order = Order(product, 1)

        val shippingCost = order.shippingCost

        Assertions.assertEquals(BigDecimal.ZERO, shippingCost)
    }
}