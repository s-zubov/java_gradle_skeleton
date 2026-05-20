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
}