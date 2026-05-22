package com.codemanship

class Order() {
    val totalExclShipping: Int = 0

    private val items: MutableMap<Int, Int> = mutableMapOf()

    constructor(product: Product, quantity: Int) : this() {
        items[product.id] = quantity
    }

    fun quantityOf(id: Int): Int = items[id] ?: 0

    fun add(product: Product, quantity: Int) {
        if (product.stockQty < product.holdQty + quantity) {
            throw InsufficientStockException(product)
        }

        items[product.id] = (items[product.id] ?: 0) + quantity

        product.hold(quantity)
    }

    fun remove(product: Product) {
        val itemCountToRemove = items[product.id] ?: 0
        items.remove(product.id)
        product.releaseHold(itemCountToRemove)
    }
}
