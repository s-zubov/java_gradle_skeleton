package com.codemanship.infra

import com.codemanship.application.Products
import com.codemanship.domain.Product
import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.MongoClient
import com.mongodb.kotlin.client.MongoCollection
import org.bson.types.ObjectId

class MongoProducts(private val uri: String) : Products {
    private val collection: MongoCollection<ProductData>
        get() {
            val mongoClient = MongoClient.create(uri)
            val database = mongoClient.getDatabase("guitars")
            return database.getCollection<ProductData>("products")
        }

    override fun get(id: String): Product {
        val objectId = ObjectId(id)
        val data = collection.find(eq("_id", objectId)).first()
        return Product(id, data.description, data.price, data.stockQty, data.holdQty)
    }

    override fun save(product: Product): Product {
        val data =
            ProductData(ObjectId(product.id), product.description, product.price, product.stockQty, product.holdQty)
        collection.replaceOne(eq("_id", ObjectId(product.id)), data)
        return product
    }
}