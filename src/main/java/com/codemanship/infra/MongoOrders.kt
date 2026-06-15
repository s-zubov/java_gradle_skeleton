package com.codemanship.infra

import com.codemanship.application.Orders
import com.codemanship.domain.Order
import com.mongodb.kotlin.client.MongoClient
import com.mongodb.kotlin.client.MongoCollection
import org.bson.types.ObjectId

class MongoOrders(private val uri: String) : Orders {
    private val collection: MongoCollection<OrderData>
        get() {
            val mongoClient = MongoClient.create(uri)
            val database = mongoClient.getDatabase("guitars")
            return database.getCollection<OrderData>("orders")
        }

    override fun save(order: Order): Order {
        val data = OrderData(
            null, order.status, order.items.values.map {
                OrderItemData(
                    ObjectId(it.product.id), it.quantity
                )
            })
        collection.insertOne(data)
        return order
    }
}