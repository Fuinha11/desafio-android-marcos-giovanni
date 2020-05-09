package com.example.desafio_android_marcos_giovanni.model.data_source

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.desafio_android_marcos_giovanni.model.Hero
import com.example.desafio_android_marcos_giovanni.network.NetworkServiceImpl
import javax.inject.Inject

abstract class HeroDataSource : PageKeyedDataSource<Int, Hero>()

class HeroDataSourceFactory : DataSource.Factory<Int, Hero>() {
    @Inject
    lateinit var heroDataSource : HeroDataSource
    override fun create(): DataSource<Int, Hero> {
        return HeroNetworkDataSource(
            NetworkServiceImpl.getInstance()
        )
    }
}