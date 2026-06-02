package com.codemanship.shippingcost

import java.math.BigDecimal

class UkShippingCostCalculation : ShippingCostCalculationStrategy() {
    override fun calculate(totalExclShipping: BigDecimal): BigDecimal {
        return if (totalExclShipping < BigDecimal("100")) BigDecimal("5.99")
        else BigDecimal.ZERO
    }
}