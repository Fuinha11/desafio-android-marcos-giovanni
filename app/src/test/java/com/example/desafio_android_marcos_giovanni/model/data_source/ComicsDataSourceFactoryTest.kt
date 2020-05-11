package com.example.desafio_android_marcos_giovanni.model.data_source

import io.kotest.matchers.nulls.shouldNotBeNull
import org.junit.Test

class ComicsDataSourceFactoryTest {
    @Test
    fun `should provide data source`() {
        val dataSource = ComicsDataSourceFactory.create()
        dataSource.shouldNotBeNull()
    }
}