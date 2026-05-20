package com.codemanship

class Order {
    fun addItem(item: Item) {
        items.add(item)
    }

    fun quantityOf(id: Int): Int = items.single { it.id == id }.quantity

    private val items: MutableList<Item> = mutableListOf()
}
