package com.example.desafio_android_marcos_giovanni.network.dto

import com.example.desafio_android_marcos_giovanni.model.Hero
import java.io.Serializable

class HeroDto (
var id: Int,
var name: String,
var description: String,
var modified: String,
var thumbnail: Thumbnail
) : Serializable {
    fun toModel(): Hero {
        return Hero(
            id,
            name,
            description,
            thumbnail.path + "." + thumbnail.extension
        )
    }
}