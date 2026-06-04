package com.codemanship.shippingcost

import java.math.BigDecimal

object EuShippingPolicy : ShippingPolicy() {
    override fun getCost(totalExclShipping: BigDecimal): BigDecimal {
        return if (totalExclShipping < BigDecimal("100")) BigDecimal("9.99")
        else BigDecimal("5.99")
    }
}