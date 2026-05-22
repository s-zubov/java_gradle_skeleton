package com.codemanship

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CalculateOrderTotalExcludingShippingTests {

    @Test
    fun `for an order with no items the total excluding shipping is 0`() {
        val order = Order()

        Assertions.assertEquals(0, order.totalExclShipping)
    }
}