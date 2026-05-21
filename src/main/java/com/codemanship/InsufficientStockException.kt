package com.codemanship

class InsufficientStockException(product: Product) :
    RuntimeException("Insufficient stock of ${product.description}. Only ${product.stockQty} currently available.")
