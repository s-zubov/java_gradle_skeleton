package com.codemanship.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class RemoveItemFromOrderTests {

    @Nested
    inner class `customer removes the item from the order` {

        @Test
        fun `the order contains no item`() {
            val product = Product(
                id = "327",
                description = "Ibanez Tube Screamer",
                price = BigDecimal("159.99"),
                stockQty = 7,
                holdQty = 2
            )
            val order = Order(OrderItem(product, 2))

            order.remove(product)

            Assertions.assertEquals(0, order.quantityOf("327"))
        }

        @Test
        fun `temporary hold is released from the product stock`() {
            val product = Product(
                id = "327",
                description = "Ibanez Tube Screamer",
                price = BigDecimal("159.99"),
                stockQty = 7,
                holdQty = 2
            )
            val order = Order(OrderItem(product, 2))

            order.remove(product)

            Assertions.assertEquals(0, product.holdQty)
        }
    }


}