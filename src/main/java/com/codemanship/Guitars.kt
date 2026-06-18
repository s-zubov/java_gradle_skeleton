package com.codemanship

import com.codemanship.application.OrderCommands
import com.codemanship.infra.MongoOrders
import com.codemanship.infra.MongoProducts
import com.mongodb.kotlin.client.MongoClient

fun main() {
    val mongoConnection = "mongodb://username:password@127.0.0.1/"
    val database = MongoClient.create(mongoConnection).getDatabase("guitars")
    val products = MongoProducts(database)
    val orders = MongoOrders(database)

    val commands = OrderCommands(products, orders)

    commands.addItem("6a34082cc889fbda459df8a4", 1)
}
