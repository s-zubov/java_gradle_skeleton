package com.codemanship

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class CalculateOrderTotalExcludingShippingTests {

    @Test
    fun `for an order with no items the total excluding shipping is 0`() {
        val order = Order()

        Assertions.assertEquals(BigDecimal.ZERO, order.totalExclShipping)
    }

    @Test
    fun `for an order with one item`() {
        val product = Product(id = 327, price = BigDecimal("159.95"), stockQty = 7, holdQty = 1)
        val order = Order(product, 1)

        Assertions.assertEquals(BigDecimal("159.95"), order.totalExclShipping)
    }
}