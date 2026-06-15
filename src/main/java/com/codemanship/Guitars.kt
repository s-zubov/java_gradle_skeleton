package com.codemanship

import com.codemanship.application.OrderCommands
import com.codemanship.application.Orders
import com.codemanship.domain.Order
import com.codemanship.infra.MongoOrders
import com.codemanship.infra.MongoProducts

fun main() {
    val mongoConnection = "mongodb://username:password@127.0.0.1/"
    val products = MongoProducts(mongoConnection)
    val orders = MongoOrders(mongoConnection)

    val commands = OrderCommands(products, orders)

    commands.addItem("6a301dcba9b2464e749df8a4", 1)
}

class FakeOrders : Orders {
    override fun save(order: Order): Order {
        return order
    }
}

