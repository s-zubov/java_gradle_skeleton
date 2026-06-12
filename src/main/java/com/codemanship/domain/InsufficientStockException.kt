package com.codemanship.domain

class InsufficientStockException(product: Product) :
    RuntimeException("Insufficient stock of ${product.description}. Only ${product.availableQty} currently available.")
