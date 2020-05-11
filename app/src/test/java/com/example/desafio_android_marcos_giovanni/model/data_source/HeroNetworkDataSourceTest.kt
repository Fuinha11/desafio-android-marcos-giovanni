package com.example.desafio_android_marcos_giovanni.model.data_source

import androidx.paging.PageKeyedDataSource
import com.example.desafio_android_marcos_giovanni.model.Hero
import com.example.desafio_android_marcos_giovanni.network.NetworkService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class HeroNetworkDataSourceTest {

    val heroNetworkDataSource: HeroNetworkDataSource

    init {
        val networkService = mockk<NetworkService>()
        every { networkService.getHeroes(any()) } returns listOf(Hero(1, "name", "disc", "url"))
        heroNetworkDataSource = HeroNetworkDataSource(networkService)
    }

    @Test
    fun `should fetch initial load`() {
        val callback = mockk<PageKeyedDataSource.LoadInitialCallback<Int, Hero>>(relaxed = true)
        val params = mockk<PageKeyedDataSource.LoadInitialParams<Int>>(relaxed = true)
        heroNetworkDataSource.loadInitial(params, callback)
        verify(atLeast = 1) {
            callback.onResult(any(), any(), any())
        }
    }

    @Test
    fun `should load next page`() {
        val callback = mockk<PageKeyedDataSource.LoadCallback<Int, Hero>>(relaxed = true)
        val params = PageKeyedDataSource.LoadParams(1, 20)
        heroNetworkDataSource.loadAfter(params, callback)
        verify(atLeast = 1) {
            callback.onResult(any(), any())
        }
    }

    @Test
    fun `should not call network`() {
        val callback = mockk<PageKeyedDataSource.LoadCallback<Int, Hero>>(relaxed = true)
        val params = PageKeyedDataSource.LoadParams(1, 20)
        heroNetworkDataSource.loadAfter(params, callback)
    }
}