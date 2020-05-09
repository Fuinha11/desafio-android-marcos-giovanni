package com.example.desafio_android_marcos_giovanni.ui.comic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android_marcos_giovanni.model.Comics
import com.example.desafio_android_marcos_giovanni.model.data_source.ComicsDataSource
import com.example.desafio_android_marcos_giovanni.model.data_source.ComicsDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ComicsViewModel : ViewModel() {
    private var heroId: Int = 0
    var mostExpensiveComics: MutableLiveData<Comics> = MutableLiveData()
    private val comicsDataSource: ComicsDataSource = ComicsDataSourceFactory.create()

    fun setHeroId(heroId: Int) {
        this.heroId = heroId
        val uiScope = CoroutineScope(Dispatchers.IO)
        uiScope.launch { getMostExpensiveComics() }
    }

    private suspend fun getMostExpensiveComics() {
        val comicsForHero = comicsDataSource.getComicsForHero(heroId)
        comicsForHero.sortedBy { it.price }
        if (comicsForHero.isNotEmpty())
            viewModelScope.launch { mostExpensiveComics.value = comicsForHero.first() }
    }
}