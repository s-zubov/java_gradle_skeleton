package com.codemanship

import java.math.BigDecimal

class Order(
    private var deliveryCountry: Country? = null, private val shipping: Shipping = DefaultShipping
) {
    constructor(
        products: List<Pair<Product, Int>>, deliveryCountry: Country? = null, shipping: Shipping = DefaultShipping
    ) : this(deliveryCountry, shipping) {
        products.forEach { (product, quantity) ->
            items[product.id] = OrderItem(product, quantity)
        }
    }

    constructor(
        product: Product, quantity: Int, deliveryCountry: Country? = null, shipping: Shipping = DefaultShipping
    ) : this(
        listOf(product to quantity), deliveryCountry, shipping
    )

    private val items: MutableMap<Int, OrderItem> = mutableMapOf()

    var status: OrderStatus = OrderStatus.Open
        private set

    val totalExclShipping: BigDecimal
        get() {
            return items.values.sumOf { item -> item.price * item.quantity.toBigDecimal() }
        }

    val shippingCost: BigDecimal
        get() {
            val deliverTo = deliveryCountry
            checkNotNull(deliverTo) { "Delivery Country is not set" }
            return shipping.cost(deliverTo, totalExclShipping)
        }

    fun quantityOf(id: Int): Int = items[id]?.quantity ?: 0

    fun add(product: Product, quantity: Int) {
        if (product.stockQty < product.holdQty + quantity) {
            throw InsufficientStockException(product)
        }

        val existingItem: OrderItem? = items[product.id]
        if (existingItem != null) {
            existingItem.add(quantity)
        } else {
            items[product.id] = OrderItem(product, quantity)
        }

        product.hold(quantity)
    }

    fun remove(product: Product) {
        val itemCountToRemove = items[product.id]?.quantity ?: 0
        items.remove(product.id)
        product.releaseHold(itemCountToRemove)
    }

    fun confirm() {
        items.forEach { (_, orderItem) ->
            orderItem.product.reduceStock(orderItem.quantity)
            orderItem.product.releaseHold(orderItem.quantity)
        }
        status = OrderStatus.Confirmed
    }
}

