package com.codemanship.shippingcost

import java.math.BigDecimal

abstract class ShippingCostCalculationStrategy {

    abstract fun calculate(totalExclShipping: BigDecimal): BigDecimal
}