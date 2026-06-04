package com.codemanship

import com.codemanship.shippingcost.EuShippingPolicy
import com.codemanship.shippingcost.RestOfTheWorldShippingPolicy
import com.codemanship.shippingcost.UkShippingPolicy
import java.math.BigDecimal

interface Shipping {
    fun cost(country: Country, totalExcludingShipping: BigDecimal): BigDecimal
}

object DefaultShipping : Shipping {
    override fun cost(country: Country, totalExcludingShipping: BigDecimal): BigDecimal {
        val policy = when (country) {
            Country.UK -> UkShippingPolicy
            Country.Germany -> EuShippingPolicy
            Country.France -> EuShippingPolicy
            else -> RestOfTheWorldShippingPolicy
        }
        return policy.getCost(totalExcludingShipping)
    }
}
