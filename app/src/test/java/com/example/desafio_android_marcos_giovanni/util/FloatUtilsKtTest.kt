package com.example.desafio_android_marcos_giovanni.util

import io.kotest.matchers.shouldBe
import org.junit.Test

class FloatUtilsKtTest {

    @Test
    fun toCurrency() {
        val float = 5000F.toCurrency()
        float shouldBe "$5,000.00"
    }
}