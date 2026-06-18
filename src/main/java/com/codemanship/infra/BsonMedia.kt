package com.codemanship.infra

import com.codemanship.domain.Media
import com.codemanship.domain.Printable
import org.bson.Document

class BsonMedia : Media {

    val document = Document()

    override fun with(key: String, value: Any): Media {
        when (value) {
            is Iterable<*> -> {
                val nestedDocument = value.map {
                    if (it is Printable) {
                        val bsonMedia = BsonMedia()
                        it.print(bsonMedia)
                        bsonMedia.document
                    } else {
                        it
                    }
                }
                document.append(key, nestedDocument)
            }

            is Printable -> {
                val bsonMedia = BsonMedia()
                value.print(bsonMedia)
                document.append(key, bsonMedia.document)
            }

            else -> {
                document.append(key, value)
            }
        }
        return this
    }

}