package com.codemanship

class Order {
    fun addItem(item: Item) {
        items.add(item)
    }

    fun quantityOf(id: Int): Int = items.singleOrNull { it.id == id }?.quantity ?: 0

    private val items: MutableList<Item> = mutableListOf()
}
