package com.codemanship

import java.math.BigDecimal

class Product {
    constructor(id: Int, holdQty: Int = 0, stockQty: Int, price: BigDecimal) {
        this.id = id
        this.holdQty = holdQty
        this.stockQty = stockQty
        this.price = price
    }

    val id: Int

    var holdQty: Int
        private set

    var stockQty: Int
        private set

    val price: BigDecimal

    val description: String = "Ibanez Tube Screamer"

    val availableQty: Int
        get() = stockQty - holdQty

    fun hold(quantity: Int) {
        holdQty += quantity
    }

    fun releaseHold(quantity: Int) {
        holdQty -= quantity
    }
}