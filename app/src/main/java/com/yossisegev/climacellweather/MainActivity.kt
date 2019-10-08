package com.kinecosystem.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yossisegev.climacellweather.country.CapitalsVMFactory
import com.yossisegev.climacellweather.country.CapitalsViewModel
import com.yossisegev.climacellweather.country.data.CountryRepository
import com.yossisegev.climacellweather.weather.ForecastVMFactory
import com.yossisegev.climacellweather.weather.ForecastViewModel
import com.yossisegev.climacellweather.weather.data.WeatherApi
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countryRepo: CountryRepository by inject()
        val weatherApi: WeatherApi by inject()

        val vm = ViewModelProviders.of(this, ForecastVMFactory(weatherApi))
            .get(ForecastViewModel::class.java)


        val capitalsViewModel = ViewModelProviders.of(this, CapitalsVMFactory(countryRepo))
            .get(CapitalsViewModel::class.java)



        capitalsViewModel.capitalsData.observe(this, Observer {
            Log.d("testing", "ok ${it.size}")
        })

        capitalsViewModel.getCapitals()

//        vm.forecastData.observe(this, Observer {
//            Log.d("testing", "ok ${it.size}")
//        })
//        vm.getForecastFor(40.7800, -73.9670)


    }
}
