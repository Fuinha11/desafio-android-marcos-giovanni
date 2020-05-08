package com.example.desafio_android_marcos_giovanni.network.dto

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Thumbnail (
    var path: String,
    var extension: String?
) : Serializable