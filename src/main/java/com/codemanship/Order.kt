package com.codemanship

import com.codemanship.shippingcost.EuShippingCostCalculation
import com.codemanship.shippingcost.RestOfTheWorldShippingCostCalculation
import com.codemanship.shippingcost.ShippingZone
import com.codemanship.shippingcost.UkShippingCostCalculation
import java.math.BigDecimal

class Order() {
    constructor(product: Product, quantity: Int) : this() {
        items[product.id] = OrderItem(product, quantity)
    }

    constructor(products: List<Pair<Product, Int>>) : this() {
        products.forEach { (product, quantity) ->
            items[product.id] = OrderItem(product, quantity)
        }
    }

    constructor(product: Product, quantity: Int, deliveryCountry: Country) : this(product, quantity) {
        this.deliveryCountry = deliveryCountry
    }

    private val shippingZone: ShippingZone
        get() = when (deliveryCountry) {
            Country.UK -> ShippingZone.UK
            Country.Germany -> ShippingZone.EU
            Country.France -> ShippingZone.EU
            else -> ShippingZone.Other
        }

    private var deliveryCountry: Country? = null

    private val items: MutableMap<Int, OrderItem> = mutableMapOf()

    val totalExclShipping: BigDecimal
        get() {
            return items.values.sumOf { item -> item.price * item.quantity.toBigDecimal() }
        }

    fun quantityOf(id: Int): Int = items[id]?.quantity ?: 0

    fun add(product: Product, quantity: Int) {
        if (product.stockQty < product.holdQty + quantity) {
            throw InsufficientStockException(product)
        }

        val existingItem: OrderItem? = items[product.id]
        if (existingItem != null) {
            existingItem.add(quantity)
        } else {
            items[product.id] = OrderItem(product, quantity)
        }

        product.hold(quantity)
    }

    fun remove(product: Product) {
        val itemCountToRemove = items[product.id]?.quantity ?: 0
        items.remove(product.id)
        product.releaseHold(itemCountToRemove)
    }

    val shippingCost: BigDecimal
        get() {
            return when (shippingZone) {
                ShippingZone.UK -> UkShippingCostCalculation().calculate(totalExclShipping)

                ShippingZone.EU -> EuShippingCostCalculation().calculate(totalExclShipping)

                ShippingZone.Other -> RestOfTheWorldShippingCostCalculation().calculate(totalExclShipping)
            }
        }
}

