package com.codemanship

import com.codemanship.Product
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AddItemToOrderTests {
    @Test
    fun `add an item when sufficient stock is available puts a temporary hold`() {
        val product = Product()
        Assertions.assertEquals(1, product.hold)
    }

    @Test
    fun `add an item when sufficient stock is available sets order quantity 1`() {
        val order = Order()
        val item = Item(327, 1)
        order.addItem(item)
        Assertions.assertEquals(1, order.quantityOf(327))
    }
}