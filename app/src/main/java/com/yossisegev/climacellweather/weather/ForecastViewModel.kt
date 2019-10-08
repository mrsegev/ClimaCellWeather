package com.yossisegev.climacellweather.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yossisegev.climacellweather.weather.api.WeatherApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ForecastViewModel(private val weatherApi: WeatherApi) : ViewModel() {

    var forecastData = MutableLiveData<List<SimpleTemp>>()

    fun getForecastFor(lat: Double, lon: Double) {

        weatherApi.getDailyForecast(lat, lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->

                Log.d("testing", "ok ${res.size}")

                val simpleTemp = res.map { SimpleTemp(it.temp, it.observationTime) }
                Log.d("testing", "ok ${simpleTemp.size}")
                forecastData.postValue(simpleTemp)

            }, { err ->
                Log.e("testing", err.localizedMessage)
            })
    }



    override fun onCleared() {
        super.onCleared()
    }
}