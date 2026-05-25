package com.codemanship

import java.math.BigDecimal

data class Product(val id: Int, var holdQty: Int = 0, var stockQty: Int, val price: BigDecimal) {
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