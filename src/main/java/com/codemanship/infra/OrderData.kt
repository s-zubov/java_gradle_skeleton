package com.codemanship.infra

import com.codemanship.domain.OrderStatus
import org.bson.types.ObjectId

data class OrderData(
    val id: ObjectId?, val status: OrderStatus, val items: List<OrderItemData>
)

data class OrderItemData(
    val productId: ObjectId, val quantity: Int
)
