package com.example.desafio_android_marcos_giovanni.network

import com.example.desafio_android_marcos_giovanni.model.Hero

interface NetworkService {
    fun getHeroes(page: Int): List<Hero>
}