package com.codemanship

class Order() {
    private val items: MutableList<Item> = mutableListOf()

    constructor(product: Product, quantity: Int) : this() {
        val item = Item(product.id, quantity)
        items.add(item)
    }

    fun quantityOf(id: Int): Int = items.singleOrNull { it.id == id }?.quantity ?: 0

    fun add(product: Product, quantity: Int) {
        if (product.stockQty < product.holdQty + quantity) {
            throw InsufficientStockException(product)
        }

        val item = Item(product.id, quantity)
        items.add(item)
        product.hold(quantity)
    }

    fun remove(product: Product) {
        val itemToRemove = items.single { it.id == product.id }
        items.remove(itemToRemove)
        product.releaseHold(itemToRemove.quantity)
    }
}
