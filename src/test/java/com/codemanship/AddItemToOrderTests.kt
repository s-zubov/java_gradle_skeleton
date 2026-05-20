package com.codemanship

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class AddItemToOrderTests {
    @Nested
    inner class `add an item when sufficient stock is available` {
        val order = Order()
        val product = Product(327, stockQty = 7)

        @Test
        fun `puts a temporary hold`() {
            order.add(product, 1)

            Assertions.assertEquals(1, product.holdQty)
        }

        @Test
        fun `sets order quantity to 1`() {
            order.add(product, 1)

            Assertions.assertEquals(1, order.quantityOf(327))
        }
    }

    @Nested
    inner class `add an item when insufficient stock is available and no stock is on hold` {
        val order = Order()
        val product = Product(id = 327, stockQty = 1)

        @Test
        fun `order contains no items`() {
            runCatching { order.add(product, 2) }

            Assertions.assertEquals(0, order.quantityOf(327))
        }

        @Test
        fun `insufficient stock error is raised`() {
             try {
                 order.add(product, 2)
             } catch (e: InsufficientStockException) {
                 Assertions.assertEquals("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.", e.message)
                 return
             }
            fail("Should have thrown an error")
        }
    }
}