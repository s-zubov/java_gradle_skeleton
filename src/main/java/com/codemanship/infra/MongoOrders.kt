package com.codemanship.infra

import com.codemanship.application.Orders
import com.codemanship.domain.Order
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document

class MongoOrders(database: MongoDatabase) : Orders {
    private val collection = database.getCollection<Document>("orders")

    override fun save(order: Order): Order {
        val bsonMedia = BsonMedia()
        order.print(bsonMedia)
        collection.insertOne(bsonMedia.document)
        return order
    }
}