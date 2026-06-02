package com.codemanship

class Customer(private val country: Country) {
    fun createOrder(product: Product, quantity: Int) = Order(product, quantity, country)
}