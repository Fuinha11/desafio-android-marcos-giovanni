package com.example.desafio_android_marcos_giovanni.model.data_source

import com.example.desafio_android_marcos_giovanni.model.Comics
import com.example.desafio_android_marcos_giovanni.network.NetworkService
import com.example.desafio_android_marcos_giovanni.network.NetworkServiceImpl

class ComicsNetworkDataSource(val networkService: NetworkService) : ComicsDataSource {

    override fun getComicsForHero(heroId: Int): List<Comics> {
        return networkService.getComicsForHero(heroId)
    }
}

class ComicsDataSourceFactory {
    companion object {
        fun create(): ComicsDataSource {
            return ComicsNetworkDataSource(NetworkServiceImpl.getInstance())
        }
    }
}