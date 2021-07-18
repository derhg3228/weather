package com.example.weaher

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val degreesView = findViewById<TextView>(R.id.degrees)

        /*
        Магия с интернетом
         */
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Сам сервис
        val service = retrofit.create(ApiService::class.java)

        // Обработка ответа с сервера
        service.getWeather().enqueue(object : Callback<ApiModel> {
            //Если ошибка
            override fun onFailure(call: Call<ApiModel>, t: Throwable) {
                Log.e(MainActivity::class.simpleName, t.message.toString())
            }

            //Если пришёл ответ
            override fun onResponse(call: Call<ApiModel>, response: Response<ApiModel>) {
                val model = response.body()

                if (model != null){
                    val degrees = (model.main.temp - 273.15).toInt()
                    runOnUiThread {
                        degreesView.text = "$degrees C"
                    }
                }
            }
        })
    }

}