package com.codemanship

import com.codemanship.shippingcost.ShippingPolicy
import java.math.BigDecimal

class Order(
    private val shippingPolicies: ShippingPolicies = DefaultShippingPolicies
) {
    constructor(product: Product, quantity: Int, shippingPolicies: ShippingPolicies = DefaultShippingPolicies) : this(
        shippingPolicies
    ) {
        items[product.id] = OrderItem(product, quantity)
    }

    constructor(
        products: List<Pair<Product, Int>>,
        shippingPolicies: ShippingPolicies = DefaultShippingPolicies
    ) : this(
        shippingPolicies
    ) {
        products.forEach { (product, quantity) ->
            items[product.id] = OrderItem(product, quantity)
        }
    }

    constructor(
        product: Product,
        quantity: Int,
        deliveryCountry: Country,
        shippingPolicies: ShippingPolicies = DefaultShippingPolicies
    ) : this(product, quantity, shippingPolicies) {
        this.deliveryCountry = deliveryCountry
        this.shippingPolicy = shippingPolicies.get(deliveryCountry)
    }

    private var shippingPolicy: ShippingPolicy? = null

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
        get() = shippingPolicy?.getCost(totalExclShipping)
            ?: throw IllegalStateException("must specify delivery country")
}

