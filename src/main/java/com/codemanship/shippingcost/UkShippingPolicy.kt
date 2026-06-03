package com.codemanship.shippingcost

import java.math.BigDecimal

class UkShippingPolicy : ShippingPolicy() {
    override fun getCost(totalExclShipping: BigDecimal): BigDecimal {
        return if (totalExclShipping < BigDecimal("100")) BigDecimal("5.99")
        else BigDecimal.ZERO
    }
}