package com.codemanship

class Order {
    fun addItem(item: Item) {
        items.add(item)
    }

    val items: MutableList<Item> = mutableListOf()
}
