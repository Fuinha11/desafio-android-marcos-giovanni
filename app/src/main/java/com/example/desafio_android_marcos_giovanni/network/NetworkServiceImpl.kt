package com.example.desafio_android_marcos_giovanni.network

import com.example.desafio_android_marcos_giovanni.model.Comics
import com.example.desafio_android_marcos_giovanni.model.Hero
import com.example.desafio_android_marcos_giovanni.network.dto.ComicListResponse
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
        val call = marvelApi.getHeroes(
            publicKey,
            pageSize,
            page * pageSize,
            ts,
            hash
        ).execute()

        val body = call.body()
        if (call.isSuccessful && body != null) {
            val response = ArrayList<Hero>()
            body.data?.results?.forEach { h ->
                response.add(
                    h.toModel()
                )
            }
            return response
        }
        return emptyList()
    }

    override fun getComicsForHero(heroId: Int): List<Comics> {
        val list = ArrayList<Comics>()
        var page = 0
        var total = 1

        //this is not the best way to go about it
        //The correct way was to order/filter it on the server side
        //Since we don't have this option i'm looping trough all comics
        while (list.size < total) {
            val response = getComicsForHeroPaged(heroId, page)
            total = response?.total ?: total
            page++
            response?.results?.forEach { c -> list.add(c.toModel()) }
        }

        return list
    }

    private fun getComicsForHeroPaged(heroId: Int, page: Int): ComicListResponse? {
        val call = marvelApi.getComicsForHero(
            heroId,
            publicKey,
            pageSize,
            pageSize * page,
            ts,
            hash
        ).execute()

        return call.body()?.data
    }

    companion object {
        val service = NetworkServiceImpl()

        fun getInstance(): NetworkServiceImpl = service
    }
}