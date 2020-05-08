package com.example.desafio_android_marcos_giovanni.network.dto

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class HeroListResponse (
    var offset: Int?,
    var limit: Int?,
    var total: Int?,
    var count: Int?,
    var results: List<HeroDto>?
) : Serializable