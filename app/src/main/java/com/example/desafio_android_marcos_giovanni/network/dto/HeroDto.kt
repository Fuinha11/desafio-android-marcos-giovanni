package com.example.desafio_android_marcos_giovanni.network.dto

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class HeroDto (
var id: Int,
var name: String,
var description: String,
var modified: String,
var thumbnail: Thumbnail
) : Serializable