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

    constructor(customer: Customer, product: Product, quantity: Int) : this(product, quantity) {
        this.customer = customer
    }

    private var customer: Customer? = null

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
            val country = customer?.country
            checkNotNull(country) { "Can't calculate shipping cost without country." }
            return if (country == "UK") {
                if (totalExclShipping < BigDecimal("100")) BigDecimal("5.99")
                else BigDecimal.ZERO
            } else {
                if (totalExclShipping < BigDecimal("100")) BigDecimal("9.99")
                else BigDecimal("5.99")
            }
        }
}

