package com.example.desafio_android_marcos_giovanni.ui.herolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.desafio_android_marcos_giovanni.model.Hero
import com.example.desafio_android_marcos_giovanni.model.data_source.HeroDataSourceFactory

class HeroListViewModel : ViewModel {
    val heroList : LiveData<PagedList<Hero>>
    var selectedHero : Hero? = null

    constructor() {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(true)
            .build()
        heroList = LivePagedListBuilder(HeroDataSourceFactory(), config).build()
    }

    fun selectHero(index: Int) {
        selectedHero = heroList.value?.get(index)
    }
}
