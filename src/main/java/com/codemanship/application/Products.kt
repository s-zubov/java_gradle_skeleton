package com.codemanship.application

import com.codemanship.domain.Product

interface Products {
    fun get(id: Int): Product
    fun save(product: Product): Product
}
