package com.codemanship.shippingcost

import java.math.BigDecimal

class RestOfTheWorldShippingPolicy : ShippingPolicy() {
    override fun calculate(totalExclShipping: BigDecimal): BigDecimal {
        return if (totalExclShipping < BigDecimal("100")) BigDecimal("12.99")
        else BigDecimal("9.99")
    }
}