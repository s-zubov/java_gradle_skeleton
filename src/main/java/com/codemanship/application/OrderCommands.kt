package com.codemanship.application

import com.codemanship.domain.Order

class OrderCommands(val products: Products, val orders: Orders) {

    fun addItem(id: String, quantity: Int) {
        val order = Order()
        val product = products.get(id)
        order.add(product, quantity)
        products.save(product)
        orders.save(order)
    }
}