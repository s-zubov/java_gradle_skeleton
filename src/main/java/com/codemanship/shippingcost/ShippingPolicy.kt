package com.codemanship.shippingcost

import java.math.BigDecimal

interface ShippingPolicy {
    fun getCost(totalExclShipping: BigDecimal): BigDecimal
}