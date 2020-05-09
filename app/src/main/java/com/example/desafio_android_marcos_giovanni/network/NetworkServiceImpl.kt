package com.example.desafio_android_marcos_giovanni.network

import com.example.desafio_android_marcos_giovanni.model.Comics
import com.example.desafio_android_marcos_giovanni.model.Hero
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServiceImpl : NetworkService {
    val marvelApi: MarvelApiInterface
    val baseUrl = "https://gateway.marvel.com:443"
    val publicKey = "0ccfa39395a520b9de0cdade634cbaa0"
    val hash = "4ab6f1cf0352b5d7b90f3deb4842d27c"
    val ts = "1"
    val pageSize = 20

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        marvelApi = retrofit.create(MarvelApiInterface::class.java)
    }

    override fun getHeroes(page: Int): List<Hero> {
        val response = performCall(
            marvelApi.getHeroes(
            publicKey,
            pageSize,
            page * pageSize,
            ts,
            hash
            )
        )
        val body = response.body()
        if (body != null) {
            val list = ArrayList<Hero>()
            body.data?.results?.forEach { h ->
                list.add(
                    h.toModel()
                )
            }
            return list
        }
        return emptyList()
    }

    override fun getComicsForHero(heroId: Int): List<Comics> {
        val list = ArrayList<Comics>()
        var page = 0
        var total = 0

        //this is not the best way to go about it
        //The correct way was to order/filter it on the server side
        //Since we don't have this option i'm looping trough all comics
        do {
            val call = performCall(
                marvelApi.getComicsForHero(
                    heroId,
                    publicKey,
                    pageSize,
                    pageSize * page,
                    ts,
                    hash
                )
            )
            val response = call.body()?.data
            total = response?.total ?: total
            page++
            response?.results?.forEach { c -> list.add(c.toModel()) }
        } while (list.size < total)

        return list
    }

    private fun <T> performCall(call: Call<T>): Response<T> {
        //This method is to deal with errors in the Api and to retry calls if needed
        var success: Boolean
        var retryCount = 0
        var response: Response<T>
        do {
            val clone = call.clone()
            response = clone.execute()
            success = response.isSuccessful && response.code() == 200
            retryCount++
        } while (!success || retryCount < 3)

        return response
    }

    companion object {
        val service = NetworkServiceImpl()

        fun getInstance(): NetworkServiceImpl = service
    }
}