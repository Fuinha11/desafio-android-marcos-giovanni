package com.example.desafio_android_marcos_giovanni.service

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.desafio_android_marcos_giovanni.model.Hero
import com.example.desafio_android_marcos_giovanni.network.NetworkService
import dagger.Provides
import javax.inject.Inject

class HeroDataSource @Inject constructor(@Inject var networkService: NetworkService) : PageKeyedDataSource<Int, Hero>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Hero>
    ) {
        var heroes = networkService.getHeroes(0)
        callback.onResult(heroes, 0, 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Hero>) {
        var heroes = networkService.getHeroes(params.key)
        callback.onResult(heroes, params.key + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Hero>) {}
}
class HeroDataSourceFactory : DataSource.Factory<Int, Hero>() {
    @Inject
    lateinit var heroDataSource : HeroDataSource
    override fun create(): DataSource<Int, Hero> {
        return HeroDataSource(NetworkService.getInstance())
    }
}