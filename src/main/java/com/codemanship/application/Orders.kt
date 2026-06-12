package com.codemanship.application

import com.codemanship.domain.Order

interface Orders {
    fun save(order: Order): Order

}
