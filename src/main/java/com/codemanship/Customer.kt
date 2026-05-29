package com.codemanship

class Customer(private val country: String) {
    fun createOrder(product: Product, quantity: Int) = Order(product, quantity, country)
}