package com.codemanship

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class AddItemToOrderTests {
    @Nested
    inner class `add an item when sufficient stock is available` {
        @Test
        fun `puts a temporary hold`() {
            val product = Product()
            Assertions.assertEquals(1, product.hold)
        }

        @Test
        fun `sets order quantity to 1`() {
            val order = Order()
            val item = Item(327, 1)
            order.addItem(item)
            Assertions.assertEquals(1, order.quantityOf(327))
        }
    }
}