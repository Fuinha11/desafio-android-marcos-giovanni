package com.example.desafio_android_marcos_giovanni.network.dto

import java.io.Serializable


class HeroListResponse (
    var offset: Int?,
    var limit: Int?,
    var total: Int?,
    var count: Int?,
    var results: List<HeroDto>?
) : Serializable