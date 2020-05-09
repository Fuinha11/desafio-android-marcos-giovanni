package com.example.desafio_android_marcos_giovanni.network

import com.example.desafio_android_marcos_giovanni.model.Comics
import com.example.desafio_android_marcos_giovanni.model.Hero

interface NetworkService {
    fun getHeroes(page: Int): List<Hero>
    fun getComicsForHero(heroId: Int): List<Comics>
}