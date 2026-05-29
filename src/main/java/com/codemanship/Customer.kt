package com.codemanship

data class Customer(val country: String) {
    fun createOrder(product: Product, quantity: Int) = Order(product, quantity, country)
}