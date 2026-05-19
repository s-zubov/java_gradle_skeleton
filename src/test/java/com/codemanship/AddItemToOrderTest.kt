package com.codemanship

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AddItemToOrderTest {
    @Test
    fun `adding item with sufficient stock hold that item`() {
        val stock: Stock = Stock()
        Assertions.assertEquals(1, stock.holdCount)
        val order: Order = null
        val orderedProductCount = order.products(327).count
        Assertions.assertEquals(1, orderedProductCount)
    }
}