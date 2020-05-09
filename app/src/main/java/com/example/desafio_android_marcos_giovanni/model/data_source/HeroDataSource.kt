package com.example.desafio_android_marcos_giovanni.model.data_source

import androidx.paging.PageKeyedDataSource
import com.example.desafio_android_marcos_giovanni.model.Hero

abstract class HeroDataSource : PageKeyedDataSource<Int, Hero>()
