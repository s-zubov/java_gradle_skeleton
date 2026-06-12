package com.codemanship.domain.shipping

import com.codemanship.domain.Country
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
