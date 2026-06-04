package com.codemanship

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class CancelOrderTests {
    @Test
    fun `releases hold`() {
        val ibanezProduct = Product(
            id = 327, description = "Ibanez Tube Screamer", price = BigDecimal("159.99"), stockQty = 7, holdQty = 2
        )
        val fenderProduct = Product(
            id = 811, description = "Fender Deluxe Reverb", price = BigDecimal("1799.00"), stockQty = 2, holdQty = 1
        )
        val order = Order(listOf(OrderItem(ibanezProduct, 2), OrderItem(fenderProduct, 1)))

        order.cancel()

        Assertions.assertEquals(0, ibanezProduct.holdQty)
        Assertions.assertEquals(0, fenderProduct.holdQty)
    }

    @Test
    fun `doesn't change stock`() {
        val ibanezProduct = Product(
            id = 327, description = "Ibanez Tube Screamer", price = BigDecimal("159.99"), stockQty = 7, holdQty = 2
        )
        val fenderProduct = Product(
            id = 811, description = "Fender Deluxe Reverb", price = BigDecimal("1799.00"), stockQty = 2, holdQty = 1
        )
        val order = Order(listOf(OrderItem(ibanezProduct, 2), OrderItem(fenderProduct, 1)))

        order.cancel()

        Assertions.assertEquals(7, ibanezProduct.stockQty)
        Assertions.assertEquals(2, fenderProduct.stockQty)
    }

    @Test
    fun `sets order status, Cancelled`() {
        val ibanezProduct = Product(
            id = 327, description = "Ibanez Tube Screamer", price = BigDecimal("159.99"), stockQty = 7, holdQty = 2
        )
        val fenderProduct = Product(
            id = 811, description = "Fender Deluxe Reverb", price = BigDecimal("1799.00"), stockQty = 2, holdQty = 1
        )
        val order = Order(listOf(OrderItem(ibanezProduct, 2), OrderItem(fenderProduct, 1)))

        order.cancel()

        Assertions.assertEquals(OrderStatus.Cancelled, order.status)
    }
}