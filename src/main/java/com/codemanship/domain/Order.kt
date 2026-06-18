package com.codemanship.domain

import com.codemanship.domain.shipping.DefaultShipping
import com.codemanship.domain.shipping.Shipping
import java.math.BigDecimal

class Order(
    val items: MutableMap<String, OrderItem> = mutableMapOf(),
    private var deliveryCountry: Country? = null,
    private val shipping: Shipping = DefaultShipping
) : Printable {
    constructor(
        products: List<OrderItem>, deliveryCountry: Country? = null, shipping: Shipping = DefaultShipping
    ) : this(
        products.associateBy { item -> item.product.id }.toMutableMap(), deliveryCountry, shipping
    )

    constructor(
        item: OrderItem, deliveryCountry: Country? = null, shipping: Shipping = DefaultShipping
    ) : this(
        listOf(item), deliveryCountry, shipping
    )

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

    fun quantityOf(id: String): Int = items[id]?.quantity ?: 0

    fun add(product: Product, quantity: Int) {
        product.hold(quantity)

        val existingItem: OrderItem? = items[product.id]
        if (existingItem != null) {
            existingItem.add(quantity)
        } else {
            items[product.id] = OrderItem(product, quantity)
        }
    }

    fun remove(product: Product) {
        val itemCountToRemove = items[product.id]?.quantity ?: 0
        items.remove(product.id)
        product.releaseHold(itemCountToRemove)
    }

    fun confirm() {
        items.values.forEach { orderItem ->
            orderItem.product.realise(orderItem.quantity)
        }
        status = OrderStatus.Confirmed
    }

    fun cancel() {
        items.values.forEach { orderItem ->
            orderItem.product.releaseHold(orderItem.quantity)
        }
        status = OrderStatus.Cancelled
    }

    override fun print(media: Media): Media {
        return media
            .with("status", status)
            .with("items", items.values.toList())
    }
}

