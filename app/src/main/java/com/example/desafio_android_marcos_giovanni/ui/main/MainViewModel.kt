package com.example.desafio_android_marcos_giovanni.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.desafio_android_marcos_giovanni.model.Hero
import com.example.desafio_android_marcos_giovanni.service.HeroDataSource
import com.example.desafio_android_marcos_giovanni.service.HeroDataSourceFactory

class MainViewModel : ViewModel {
    val heroList : LiveData<PagedList<Hero>>
    var selectedHero : Hero? = null

    constructor() {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(true)
            .build()
        heroList = LivePagedListBuilder(HeroDataSourceFactory(), config).build()
    }

    fun selectHero(index: Int) {
        selectedHero = heroList.value?.get(index)
    }
}
