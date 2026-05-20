package com.codemanship

data class Product(val id: Int, var holdQty: Int = 0, var stockQty: Int) {
    fun hold(quantity: Int) {
        holdQty = quantity
    }
}