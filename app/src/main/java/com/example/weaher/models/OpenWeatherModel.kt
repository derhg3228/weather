package com.example.weaher.models

import com.google.gson.annotations.SerializedName

data class OpenWeatherModel(
    val main: MainModel,
    val weather: List<WeatherModel>,
    @SerializedName("name")
    val city: String
)