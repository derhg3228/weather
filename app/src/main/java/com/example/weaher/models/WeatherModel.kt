package com.example.weaher.models

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("main")
    val weather: String,
    val description: String,
    val icon: String
)