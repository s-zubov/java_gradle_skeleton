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

    var country: String = ""

    private val items: MutableMap<Int, OrderItem> = mutableMapOf()

    val totalExclShipping: BigDecimal
        get() {
            return items.values.sumOf { item -> item.price * item.quantity.toBigDecimal() }
        }

    val shippingCost: BigDecimal
        get() {
            return if (country == "UK") {
                if (totalExclShipping < BigDecimal("100")) BigDecimal("5.99")
                else BigDecimal.ZERO
            } else {
                if (totalExclShipping < BigDecimal("100")) BigDecimal("9.99")
                else BigDecimal("5.99")
            }
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
}
