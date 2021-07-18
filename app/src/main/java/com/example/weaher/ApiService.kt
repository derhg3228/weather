package com.example.weaher

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    companion object {
        private const val API_KEY = "3624e18d4dceeb66361eb084cd0a07f7"
    }

    @GET("data/2.5/weather?q=Izhevsk&appid=$API_KEY")
    fun getWeather(): Call<ApiModel>
}