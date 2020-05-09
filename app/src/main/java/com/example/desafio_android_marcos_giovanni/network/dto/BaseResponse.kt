package com.example.desafio_android_marcos_giovanni.network.dto

import java.io.Serializable


class BaseResponse<T> (
    var code: Int?,
    var status: String?,
    var data: T?
) : Serializable