package com.codemanship

import com.codemanship.shippingcost.EuShippingPolicy
import com.codemanship.shippingcost.RestOfTheWorldShippingPolicy
import com.codemanship.shippingcost.ShippingPolicy
import com.codemanship.shippingcost.UkShippingPolicy

interface ShippingPolicies {
    fun get(country: Country): ShippingPolicy
}

object DefaultShippingPolicies : ShippingPolicies {
    override fun get(country: Country): ShippingPolicy {
        return when (country) {
            Country.UK -> UkShippingPolicy()
            Country.Germany -> EuShippingPolicy()
            Country.France -> EuShippingPolicy()
            else -> RestOfTheWorldShippingPolicy()
        }
    }
}
