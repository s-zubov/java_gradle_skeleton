package com.codemanship

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ConfirmOrderTests {

    @Test
    fun `adjusts stock`() {
        val ibanezProduct =
            Product(
                id = 327,
                description = "Ibanez Tube Screamer",
                price = BigDecimal("159.99"),
                stockQty = 7,
                holdQty = 2
            )
        val fenderProduct = Product(
            id = 811,
            description = "Fender Deluxe Reverb",
            price = BigDecimal("1799.00"),
            stockQty = 2,
            holdQty = 1
        )
        val order = Order(listOf(ibanezProduct to 2, fenderProduct to 1))

        order.confirm()

        Assertions.assertEquals(5, ibanezProduct.stockQty)
        Assertions.assertEquals(1, fenderProduct.stockQty)
    }

    @Test
    fun `releases holds`() {
        val ibanezProduct =
            Product(
                id = 327,
                description = "Ibanez Tube Screamer",
                price = BigDecimal("159.99"),
                stockQty = 7,
                holdQty = 2
            )
        val fenderProduct = Product(
            id = 811,
            description = "Fender Deluxe Reverb",
            price = BigDecimal("1799.00"),
            stockQty = 2,
            holdQty = 1
        )
        val order = Order(listOf(ibanezProduct to 2, fenderProduct to 1))

        order.confirm()

        Assertions.assertEquals(0, ibanezProduct.holdQty)
        Assertions.assertEquals(0, fenderProduct.holdQty)
    }
}