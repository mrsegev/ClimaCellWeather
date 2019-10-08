package com.yossisegev.climacellweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kinecosystem.myapplication.R
import com.yossisegev.climacellweather.weather.ForecastVMFactory
import com.yossisegev.climacellweather.weather.ForecastViewModel
import com.yossisegev.climacellweather.weather.data.WeatherApi
import kotlinx.android.synthetic.main.activity_weather.*
import org.koin.android.ext.android.inject

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val weatherApi: WeatherApi by inject()
        val vm = ViewModelProviders.of(this, ForecastVMFactory(weatherApi))
            .get(ForecastViewModel::class.java)


        vm.forecastData.observe(this, Observer {
            current_weather.text = it[0].max.toString()
        })

        vm.getForecastFor(40.7800, -73.9670)
    }
}
