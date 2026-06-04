package com.codemanship

import java.math.BigDecimal

class OrderItem {
    constructor(product: Product, quantity: Int) {
        this.product = product
        this.quantity = quantity
        this.price = product.price
    }

    val product: Product

    var quantity: Int
        private set

    val price: BigDecimal

    fun add(qty: Int) {
        quantity += qty
    }
}