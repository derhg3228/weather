package com.example.weaher

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Работа с сетью
object NetworkModule {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService() = retrofit.create(ApiService::class.java)
}