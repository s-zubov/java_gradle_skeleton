package com.codemanship.shippingcost

import java.math.BigDecimal

abstract class ShippingPolicy {

    abstract fun getCost(totalExclShipping: BigDecimal): BigDecimal
}