package com.codemanship

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class CalculateOrderTotalExcludingShippingTests {

    @Test
    fun `for an order with no items the total excluding shipping is 0`() {
        val order = Order()

        val totalExclShipping = order.totalExclShipping

        Assertions.assertEquals(BigDecimal.ZERO, totalExclShipping)
    }

    @Test
    fun `for an order with one item`() {
        val product = Product(
            id = 327, description = "Ibanez Tube Screamer", price = BigDecimal("159.95"), stockQty = 7, holdQty = 1
        )
        val order = Order(product, 1)

        val totalExclShipping = order.totalExclShipping

        Assertions.assertEquals(BigDecimal("159.95"), totalExclShipping)
    }

    @Test
    fun `for an order with two items`() {
        val product = Product(
            id = 327, description = "Ibanez Tube Screamer", price = BigDecimal("159.95"), stockQty = 7, holdQty = 1
        )
        val product2 = Product(
            id = 811, description = "Fender Deluxe Reverb", price = BigDecimal("1799.00"), stockQty = 2, holdQty = 1
        )
        val order = Order(listOf(product to 1, product2 to 1))

        val totalExclShipping = order.totalExclShipping

        Assertions.assertEquals(BigDecimal("1958.95"), totalExclShipping)
    }

    @Test
    fun `for an order with one item of two quantity`() {
        val product = Product(
            id = 327, description = "Ibanez Tube Screamer", price = BigDecimal("159.95"), stockQty = 7, holdQty = 2
        )
        val order = Order(product, 2)

        val totalExclShipping = order.totalExclShipping

        Assertions.assertEquals(BigDecimal("319.90"), totalExclShipping)
    }
}