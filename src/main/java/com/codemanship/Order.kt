package com.codemanship

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

    constructor(product: Product, quantity: Int, deliveryCountry: String) : this(product, quantity) {
        this.deliveryCountry = deliveryCountry
    }

    private val shippingZone: String
        get() = when (deliveryCountry) {
            "UK" -> "UK"
            "Germany" -> "EU"
            "France" -> "EU"
            else -> "Other"
        }

    private var deliveryCountry: String? = null

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
                "UK" -> if (totalExclShipping < BigDecimal("100")) BigDecimal("5.99")
                else BigDecimal.ZERO

                "EU" -> if (totalExclShipping < BigDecimal("100")) BigDecimal("9.99")
                else BigDecimal("5.99")

                "Other" -> if (totalExclShipping < BigDecimal("100")) BigDecimal("12.99")
                else BigDecimal("9.99")

                else -> throw IllegalStateException("Can't calculate shipping cost for shipping zone $shippingZone.")
            }
        }
}

