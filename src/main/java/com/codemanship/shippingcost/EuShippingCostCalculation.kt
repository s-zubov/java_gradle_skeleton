package com.codemanship.shippingcost

import java.math.BigDecimal

class EuShippingCostCalculation : ShippingCostCalculationStrategy() {
    override fun calculate(totalExclShipping: BigDecimal): BigDecimal {
        return if (totalExclShipping < BigDecimal("100")) BigDecimal("9.99")
        else BigDecimal("5.99")
    }
}