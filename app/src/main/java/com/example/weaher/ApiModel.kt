package com.example.weaher

data class ApiModel(
    val name: String,
    val main: MainModel
)

data class MainModel(
    val temp: Double
)