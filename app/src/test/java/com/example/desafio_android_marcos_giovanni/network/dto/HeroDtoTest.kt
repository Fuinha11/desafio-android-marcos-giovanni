package com.example.desafio_android_marcos_giovanni.network.dto

import io.kotest.matchers.shouldBe
import org.junit.Test

class HeroDtoTest {

    @Test
    fun `should convert to model correctly`() {
        val dto = HeroDto(
            1,
            "nome",
            "desc",
            Thumbnail(
                "path",
                "ext"
            )
        )
        val model = dto.toModel()

        model.id shouldBe 1
        model.name shouldBe "nome"
        model.description shouldBe "desc"
        model.imageUrl shouldBe "path.ext"
    }
}