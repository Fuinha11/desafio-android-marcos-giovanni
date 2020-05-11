package com.example.desafio_android_marcos_giovanni.network.dto

import io.kotest.matchers.shouldBe
import org.junit.Test

class ComicDtoTest {

    @Test
    fun `should convert to model correctly`() {
        val dto = ComicDto(
            1,
            "title",
            "desc",
            listOf(
                Price(
                    "one",
                    1F
                ),
                Price(
                    "two",
                    2F
                )
            ),
            Thumbnail(
                "path",
                "ext"
            )
        )
        val model = dto.toModel()

        model.id shouldBe 1
        model.title shouldBe "title"
        model.description shouldBe "desc"
        model.price shouldBe 2F
        model.imageUrl shouldBe "path.ext"
    }
}