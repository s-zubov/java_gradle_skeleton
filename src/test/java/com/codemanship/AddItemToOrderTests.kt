package com.codemanship

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class AddItemToOrderTests {
    @Nested
    inner class `add an item when sufficient stock is available` {
        @Test
        fun `puts a temporary hold`() {
            val order = Order()
            val product = Product(327, stockQty = 7)

            order.add(product, 1)

            Assertions.assertEquals(1, product.holdQty)
        }

        @Test
        fun `sets order quantity to 1`() {
            val order = Order()
            val product = Product(327, stockQty = 7)

            order.add(product, 1)

            Assertions.assertEquals(1, order.quantityOf(327))
        }
    }

    @Nested
    inner class `add an item when insufficient stock is available and no stock is on hold` {
        @Test
        fun `order contains no items`() {
            val order = Order()
            val product = Product(id = 327, stockQty = 1)

            order.add(product, 2)

            Assertions.assertEquals(0, order.quantityOf(327))
        }
    }
}