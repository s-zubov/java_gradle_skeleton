package com.codemanship

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class CalculateShippingTests {

    @Test
    fun `for UK order cost under 100`() {
        val order = Order()
        val shippingCost = order.shippingCost

        Assertions.assertEquals(BigDecimal("5.99"), shippingCost)
    }
}