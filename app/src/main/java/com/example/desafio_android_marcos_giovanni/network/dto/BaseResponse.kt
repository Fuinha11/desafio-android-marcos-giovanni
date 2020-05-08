package com.example.desafio_android_marcos_giovanni.network.dto

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class BaseResponse<T> (
    var code: Int?,
    var status: String?,
    var copyright: String?,
    var attributionText: String?,
    var attributionHTML: String?,
    var etag: String?,
    var data: T?
) : Serializable