package com.codemanship.domain

class Customer(private val country: Country) {
    fun createOrder(product: Product, quantity: Int) = Order(OrderItem(product, quantity), country)
}