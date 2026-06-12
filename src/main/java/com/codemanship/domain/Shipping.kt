package com.codemanship.domain

import com.codemanship.domain.shippingcost.EuShippingPolicy
import com.codemanship.domain.shippingcost.RestOfTheWorldShippingPolicy
import com.codemanship.domain.shippingcost.UkShippingPolicy
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
