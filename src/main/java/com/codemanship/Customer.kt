package com.codemanship

data class Customer(val country: String) {
    fun createOrder(product: Product, quantity: Int) = Order(this, product, quantity)
}