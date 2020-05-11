package com.example.desafio_android_marcos_giovanni.network

import com.example.desafio_android_marcos_giovanni.network.dto.*
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class NetworkServiceImplTest {

    val marvelApiInterface: MarvelApiInterface = mockk(relaxed = true)
    val mockHeroCall = mockk<Call<BaseResponse<HeroListResponse>>>(relaxed = true)
    val mockHeroResponse = mockk<Response<BaseResponse<HeroListResponse>>>(relaxed = true)
    val mockComicCall = mockk<Call<BaseResponse<ComicListResponse>>>(relaxed = true)
    val mockComiResponse = mockk<Response<BaseResponse<ComicListResponse>>>(relaxed = true)
    val heroDto = HeroDto(
        1,
        "nome",
        "desc",
        Thumbnail(
            "",
            ""
        )
    )
    val comicDto = ComicDto(
        1,
        "Title",
        "desc",
        listOf(
            Price(
                "lol",
                1f
            )
        ),
        Thumbnail(
            "",
            ""
        )

    )

    init {
        NetworkServiceImpl.marvelApi = marvelApiInterface
        every {
            marvelApiInterface.getHeroes(
                any(),
                any(),
                any(),
                any(),
                any()
            )
        } returns mockHeroCall
        every {
            marvelApiInterface.getComicsForHero(
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        } returns mockComicCall
    }

    @Test
    fun `should get list of heroes`() {
        every { mockHeroCall.clone() } returns mockHeroCall
        every { mockHeroCall.execute() } returns mockHeroResponse
        every { mockHeroResponse.isSuccessful } returns true
        every { mockHeroResponse.code() } returns 200
        every { mockHeroResponse.body() } returns BaseResponse(
            200,
            "ok",
            HeroListResponse(
                1,
                1,
                1,
                1,
                listOf(heroDto)
            )
        )
        val heroes = NetworkServiceImpl.getHeroes(1)
        heroes.shouldNotBeNull()
        heroes.shouldNotBeEmpty()
    }

    @Test
    fun `should get empty list of heroes`() {
        every { mockHeroCall.clone() } returns mockHeroCall
        every { mockHeroCall.execute() } returns mockHeroResponse
        every { mockHeroResponse.isSuccessful } returns true
        every { mockHeroResponse.code() } returns 200
        every { mockHeroResponse.body() } returns null
        every {
            marvelApiInterface.getHeroes(
                any(),
                any(),
                any(),
                any(),
                any()
            )
        } returns mockHeroCall
        val heroes = NetworkServiceImpl.getHeroes(1)
        heroes.shouldNotBeNull()
        heroes.shouldBeEmpty()
    }

    @Test
    fun `should get list of comics`() {
        every { mockComicCall.clone() } returns mockComicCall
        every { mockComicCall.execute() } returns mockComiResponse
        every { mockComiResponse.isSuccessful } returns true
        every { mockComiResponse.code() } returns 200
        every { mockComiResponse.body() } returns BaseResponse(
            200,
            "ok",
            ComicListResponse(
                1,
                1,
                1,
                1,
                listOf(comicDto)
            )
        )
        val comics = NetworkServiceImpl.getComicsForHero(1)
        comics.shouldNotBeNull()
        comics.shouldNotBeEmpty()
    }

    @Test
    fun `should make multiple calls for large list of comics`() {
        every { mockComicCall.clone() } returns mockComicCall
        every { mockComicCall.execute() } returns mockComiResponse
        every { mockComiResponse.isSuccessful } returns true
        every { mockComiResponse.code() } returns 200
        every { mockComiResponse.body() } returns BaseResponse(
            200,
            "ok",
            ComicListResponse(
                1,
                1,
                3,
                1,
                listOf(comicDto)
            )
        )
        val comics = NetworkServiceImpl.getComicsForHero(1)

        comics.shouldNotBeNull()
        comics.shouldNotBeEmpty()
        comics.shouldHaveSize(3)
    }

    @Test
    fun `should get empty list of comics`() {
        every { mockComicCall.clone() } returns mockComicCall
        every { mockComicCall.execute() } returns mockComiResponse
        every { mockComiResponse.isSuccessful } returns true
        every { mockComiResponse.code() } returns 200
        every { mockComiResponse.body() } returns null
        val comics = NetworkServiceImpl.getComicsForHero(1)
        comics.shouldNotBeNull()
        comics.shouldBeEmpty()
    }

    @Test
    fun `should perform call normally`() {
        every { mockComicCall.clone() } returns mockComicCall
        every { mockComicCall.execute() } returns mockComiResponse
        every { mockComiResponse.isSuccessful } returns true
        every { mockComiResponse.code() } returns 200
        val response = NetworkServiceImpl.performCall(mockComicCall)
        response.shouldNotBeNull()
    }

    @Test
    fun `should retry call if fail`() {
        every { mockComicCall.clone() } returns mockComicCall
        every { mockComicCall.execute() } returns mockComiResponse
        every { mockComicCall.execute() } returns mockComiResponse
        every { mockComiResponse.isSuccessful } returns false andThen true
        every { mockComiResponse.code() } returns 200
        val response = NetworkServiceImpl.performCall(mockComicCall)
        response.shouldNotBeNull()
        verify(atLeast = 2) {
            mockComicCall.execute()
        }
    }

    @Test
    fun `should retry at most 3 times if fail`() {
        every { mockComicCall.clone() } returns mockComicCall
        every { mockComicCall.execute() } returns mockComiResponse
        every { mockComicCall.execute() } returns mockComiResponse
        every { mockComiResponse.isSuccessful } returns false
        every { mockComiResponse.code() } returns 200
        val response = NetworkServiceImpl.performCall(mockComicCall)
        response.shouldNotBeNull()
        verify(atMost = 4) {
            mockComicCall.execute()
        }
    }
}