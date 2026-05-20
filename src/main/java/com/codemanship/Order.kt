package com.codemanship

class Order {
    fun quantityOf(id: Int): Int = items.singleOrNull { it.id == id }?.quantity ?: 0

    fun add(product: Product, quantity: Int) {
        if (product.stockQty < product.holdQty + quantity) {
            throw InsufficientStockException("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.")
        }

        val item = Item(product.id, quantity)
        items.add(item)
        product.hold(quantity)
    }

    private val items: MutableList<Item> = mutableListOf()
}
