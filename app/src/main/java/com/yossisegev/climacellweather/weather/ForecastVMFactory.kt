package com.yossisegev.climacellweather.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yossisegev.climacellweather.weather.api.WeatherApi

@Suppress("UNCHECKED_CAST")
class ForecastVMFactory(private val weatherApi: WeatherApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ForecastViewModel(weatherApi) as T
    }

}