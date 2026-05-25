package com.codemanship

import java.math.BigDecimal

class Product {
    constructor(id: Int, description: String, price: BigDecimal, stockQty: Int, holdQty: Int) {
        this.id = id
        this.holdQty = holdQty
        this.stockQty = stockQty
        this.price = price
        this.description = description
    }

    val id: Int

    var holdQty: Int
        private set

    var stockQty: Int
        private set

    val price: BigDecimal

    var description: String
        private set

    val availableQty: Int
        get() = stockQty - holdQty

    fun hold(quantity: Int) {
        holdQty += quantity
    }

    fun releaseHold(quantity: Int) {
        holdQty -= quantity
    }
}