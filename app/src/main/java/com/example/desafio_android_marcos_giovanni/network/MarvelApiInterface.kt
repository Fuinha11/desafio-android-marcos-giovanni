package com.example.desafio_android_marcos_giovanni.network

import com.example.desafio_android_marcos_giovanni.network.dto.BaseResponse
import com.example.desafio_android_marcos_giovanni.network.dto.HeroDto
import com.example.desafio_android_marcos_giovanni.network.dto.HeroListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MarvelApiInterface {

    @GET("/v1/public/characters")
    fun getHeroes(
        @Query("apikey") apiKey:String,
        @Query("limit") limit:Int,
        @Query("offset") offset:Int,
        @Query("ts") ts:String,
        @Query("hash") hash:String
    ) : Call<BaseResponse<HeroListResponse>>
}