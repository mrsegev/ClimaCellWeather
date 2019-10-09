package com.yossisegev.climacellweather.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yossisegev.climacellweather.weather.data.WeatherApi
import com.yossisegev.climacellweather.weather.entities.SimpleWeather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

class ForecastViewModel(private val weatherApi: WeatherApi) : ViewModel() {

    var forecastData = MutableLiveData<List<SimpleWeather>>()
    private val disposables = CompositeDisposable()

    fun getForecastFor(lat: Double, lon: Double) {

        val disposable = weatherApi.getDailyForecast(lat, lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->

                val simpleTemp = res.map {
                    SimpleWeather(it)
                }

                forecastData.postValue(simpleTemp)

            }, { err ->
                Log.e("testing", err.localizedMessage)
            })

        disposables.add(disposable)
    }



    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}