package com.codemanship.domain.shipping

import java.math.BigDecimal

object RestOfTheWorldShippingPolicy : ShippingPolicy {
    override fun getCost(totalExclShipping: BigDecimal): BigDecimal {
        return if (totalExclShipping < BigDecimal("100")) BigDecimal("12.99")
        else BigDecimal("9.99")
    }
}