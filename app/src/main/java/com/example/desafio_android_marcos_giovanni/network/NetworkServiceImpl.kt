package com.example.desafio_android_marcos_giovanni.network

import com.example.desafio_android_marcos_giovanni.model.Hero
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
                    Hero(
                        h.id,
                        h.name,
                        h.description,
                        h.thumbnail.path + "." + h.thumbnail.extension
                    )
                )
            }
            return response
        }
        return emptyList()
    }

    companion object {
        val service = NetworkServiceImpl()

        fun getInstance(): NetworkServiceImpl = service
    }
}