package com.codemanship

import com.codemanship.shippingcost.ShippingPolicy
import java.math.BigDecimal

class Order(
    private var deliveryCountry: Country? = null,
    private val shippingPolicies: ShippingPolicies = DefaultShippingPolicies
) {
    constructor(
        products: List<Pair<Product, Int>>,
        deliveryCountry: Country? = null,
        shippingPolicies: ShippingPolicies = DefaultShippingPolicies
    ) : this(deliveryCountry, shippingPolicies) {
        products.forEach { (product, quantity) ->
            items[product.id] = OrderItem(product, quantity)
        }
    }

    constructor(
        product: Product,
        quantity: Int,
        deliveryCountry: Country? = null,
        shippingPolicies: ShippingPolicies = DefaultShippingPolicies
    ) : this(
        listOf(product to quantity), deliveryCountry, shippingPolicies
    )

    private val shippingPolicy: ShippingPolicy?
        get() = deliveryCountry?.let { shippingPolicies.get(it) }

    private val items: MutableMap<Int, OrderItem> = mutableMapOf()

    val totalExclShipping: BigDecimal
        get() {
            return items.values.sumOf { item -> item.price * item.quantity.toBigDecimal() }
        }

    val shippingCost: BigDecimal
        get() = shippingPolicy?.getCost(totalExclShipping)
            ?: throw IllegalStateException("must specify delivery country")

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
}

