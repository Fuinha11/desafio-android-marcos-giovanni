package com.example.desafio_android_marcos_giovanni.model.data_source

import com.example.desafio_android_marcos_giovanni.model.Comics
import com.example.desafio_android_marcos_giovanni.network.NetworkService
import io.kotest.matchers.collections.shouldContainAll
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class ComicsNetworkDataSourceTest {

    val dataSource: ComicsDataSource
    val comics = Comics(
        1,
        1f,
        "title",
        "desc",
        "url"
    )

    init {
        val networkService = mockk<NetworkService>()
        every { networkService.getComicsForHero(any()) } returns listOf(comics)
        dataSource = ComicsNetworkDataSource(networkService)
    }

    @Test
    fun `should fetch list from network`() {
        val comics = dataSource.getComicsForHero(1)
        comics.shouldContainAll(
            comics
        )
    }
}