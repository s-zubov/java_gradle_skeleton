package com.codemanship

import java.math.BigDecimal

class Product {
    constructor(id: Int, description: String, price: BigDecimal, stockQty: Int, holdQty: Int) {
        this.id = id
        this.description = description
        this.price = price
        this.stockQty = stockQty
        this.holdQty = holdQty
    }

    val id: Int

    var description: String
        private set

    val price: BigDecimal

    var stockQty: Int
        private set

    var holdQty: Int
        private set

    val availableQty: Int
        get() = stockQty - holdQty

    fun hold(quantity: Int) {
        if (stockQty < holdQty + quantity) {
            throw InsufficientStockException(this)
        }
        holdQty += quantity
    }

    fun releaseHold(quantity: Int) {
        holdQty -= quantity
    }

    fun reduceStock(quantity: Int) {
        stockQty -= quantity
    }
}