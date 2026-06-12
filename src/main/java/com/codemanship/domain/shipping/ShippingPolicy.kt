package com.codemanship.domain.shipping

import java.math.BigDecimal

interface ShippingPolicy {
    fun getCost(totalExclShipping: BigDecimal): BigDecimal
}