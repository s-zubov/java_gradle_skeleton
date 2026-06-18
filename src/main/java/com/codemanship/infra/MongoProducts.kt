package com.codemanship.infra

import com.codemanship.application.Products
import com.codemanship.domain.Product
import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.MongoDatabase
import org.bson.Document
import org.bson.types.Decimal128
import org.bson.types.ObjectId

class MongoProducts(database: MongoDatabase) : Products {
    private val collection = database.getCollection<Document>("products")

    override fun get(id: String): Product {
        val objectId = ObjectId(id)
        val document = collection.find(eq("_id", objectId)).first()
        return Product(
            id,
            document.getString("description"),
            document.get(("price"), Decimal128::class.java).bigDecimalValue(),
            document.getInteger("stockQty"),
            document.getInteger("holdQty")
        )
    }

    override fun save(product: Product): Product {
        val bsonMedia = BsonMedia()
        product.print(bsonMedia)
        collection.replaceOne(
            eq("_id", ObjectId(product.id)),
            bsonMedia.document
        )
        return product
    }
}