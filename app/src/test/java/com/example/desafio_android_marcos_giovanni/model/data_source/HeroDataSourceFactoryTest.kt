package com.example.desafio_android_marcos_giovanni.model.data_source

import io.kotest.matchers.nulls.shouldNotBeNull
import org.junit.Test

class HeroDataSourceFactoryTest {

    @Test
    fun `should provide data source`() {
        val dataSource = HeroDataSourceFactory().create()
        dataSource.shouldNotBeNull()
    }
}