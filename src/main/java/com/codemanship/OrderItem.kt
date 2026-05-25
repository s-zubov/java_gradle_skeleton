package com.codemanship

data class OrderItem(private val product: Product, var quantity: Int) {
    val id = product.id

    val price = product.price

    fun add(qty: Int) {
        quantity += qty
    }
}