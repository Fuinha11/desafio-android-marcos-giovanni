package com.example.desafio_android_marcos_giovanni.network.dto

import com.example.desafio_android_marcos_giovanni.model.Comics
import java.io.Serializable
import java.util.function.Consumer

class ComicDto(
    var id: Int,
    var title: String,
    var description: String? = "",
    var prices: List<Price>,
    var thumbnail: Thumbnail
) : Serializable {
    fun toModel(): Comics {
        var largerPrice = 0F
        prices.forEach(Consumer { p ->
            run {
                if (p.price > largerPrice)
                    largerPrice = p.price
            }
        })
        return Comics(
            id,
            largerPrice,
            title,
            description ?: "",
            thumbnail.path + "." + thumbnail.extension
        )
    }
}