package com.codemanship.application

import com.codemanship.domain.Order
import com.codemanship.domain.Product
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class AddItemTests {

    @Test
    fun `puts a temporary hold`() {
        val product = Product(
            327,
            description = "Ibanez Tube Screamer",
            price = BigDecimal("159.99"),
            stockQty = 7,
            holdQty = 0
        )
        val products: Products = object : Products {
            override fun get(id: String): Product {
                return product
            }

            override fun save(product: Product): Product {
                return product
            }
        }
        val orders: Orders = object : Orders {
            override fun save(order: Order): Order {
                return order
            }
        }

        val orderCommands = OrderCommands(products, orders)
        orderCommands.addItem(327, 1)

        Assertions.assertEquals(1, product.holdQty)
    }
}