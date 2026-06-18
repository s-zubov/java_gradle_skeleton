package com.codemanship.domain

import java.math.BigDecimal

class OrderItem : Printable {
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

    override fun print(media: Media): Media {
        return media
            .with("quantity", quantity)
            .with("productId", product.id)
    }
}