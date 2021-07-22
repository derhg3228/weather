package com.example.weaher

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.weaher.databinding.ActivityMainBinding
import com.example.weaher.models.OpenWeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    //Нужно для доступа к ui элементам
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Сам сервис
        val service = NetworkModule.getService()

        // Обработка ответа с сервера
        service.getWeather().enqueue(MainCallback())
    }

    private inner class MainCallback : Callback<OpenWeatherModel> {
        //Если ошибка
        override fun onFailure(call: Call<OpenWeatherModel>, t: Throwable) {
            Log.e(MainActivity::class.simpleName, t.message.toString())
        }

        //Если пришёл ответ
        override fun onResponse(call: Call<OpenWeatherModel>, response: Response<OpenWeatherModel>) {
            val model = response.body()

            if (model != null){
                val temp = (model.main.temp - 273.15).toInt()
                runOnUiThread {
                    with(binding) {
                        degrees.text = "$temp C"
                        city.text = model.city
                        weatherDescription.text = model.weather.first().description.capitalize()
                    }
                }
            }
        }
    }
}