package com.codemanship.infra

import jdk.jfr.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import java.math.BigDecimal

@Serializable
data class ProductData(
    @SerialName("_id") @Contextual val id: ObjectId?,
    val description: String,
    val price: BigDecimal,
    val stockQty: Int,
    val holdQty: Int
)