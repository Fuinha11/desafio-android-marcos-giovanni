package com.example.desafio_android_marcos_giovanni.model.data_source

import com.example.desafio_android_marcos_giovanni.model.Comics

interface ComicsDataSource {
    fun getComicsForHero(heroId: Int): List<Comics>
}