package com.codemanship

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.math.BigDecimal

class AddItemToOrderTests {
    @Nested
    inner class `add an item when sufficient stock is available` {
        @Test
        fun `puts a temporary hold`() {
            val order = Order()
            val product = Product(
                327,
                description = "Ibanez Tube Screamer",
                price = BigDecimal("159.99"),
                stockQty = 7,
                holdQty = 0
            )

            order.add(product, 1)

            Assertions.assertEquals(1, product.holdQty)
        }

        @Test
        fun `sets order quantity to 1`() {
            val order = Order()
            val product = Product(
                327,
                description = "Ibanez Tube Screamer",
                price = BigDecimal("159.99"),
                stockQty = 7,
                holdQty = 0
            )

            order.add(product, 1)

            Assertions.assertEquals(1, order.quantityOf(327))
        }
    }

    @Nested
    inner class `add an item when insufficient stock is available and no stock is on hold` {
        @Test
        fun `order contains no items`() {
            val order = Order()
            val product = Product(
                id = 327,
                description = "Ibanez Tube Screamer",
                price = BigDecimal("159.99"),
                stockQty = 1,
                holdQty = 0
            )

            runCatching { order.add(product, 2) }

            Assertions.assertEquals(0, order.quantityOf(327))
        }

        @Test
        fun `insufficient stock error is raised`() {
            val order = Order()
            val product = Product(
                id = 327,
                description = "Ibanez Tube Screamer",
                price = BigDecimal("159.99"),
                stockQty = 1,
                holdQty = 0
            )

            try {
                order.add(product, 2)
            } catch (e: InsufficientStockException) {
                val expectedMessage = "Insufficient stock of Ibanez Tube Screamer. Only 1 currently available."
                Assertions.assertEquals(expectedMessage, e.message)
                return
            }
            fail("Should have thrown an error")
        }
    }

    @Nested
    inner class `reject adding an item when insufficient stock is available due to stock on hold` {
        @Test
        fun `order contains no items`() {
            val order = Order()
            val product = Product(
                id = 327,
                description = "Ibanez Tube Screamer",
                price = BigDecimal("159.99"),
                stockQty = 2,
                holdQty = 1
            )

            runCatching { order.add(product, 2) }

            Assertions.assertEquals(0, order.quantityOf(327))
        }

        @Test
        fun `insufficient stock error is raised`() {
            val order = Order()
            val product = Product(
                id = 327,
                description = "Ibanez Tube Screamer",
                price = BigDecimal("159.99"),
                stockQty = 2,
                holdQty = 1
            )

            try {
                order.add(product, 2)
            } catch (e: InsufficientStockException) {
                val expectedMessage = "Insufficient stock of Ibanez Tube Screamer. Only 1 currently available."
                Assertions.assertEquals(expectedMessage, e.message)
                return
            }
            fail("Should have thrown an error")
        }
    }
}