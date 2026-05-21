package com.codemanship

data class Product(val id: Int, var holdQty: Int = 0, var stockQty: Int) {
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