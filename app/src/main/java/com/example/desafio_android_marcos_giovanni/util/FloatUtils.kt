package com.example.desafio_android_marcos_giovanni.util

import java.text.NumberFormat

fun Float.toCurrency(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}