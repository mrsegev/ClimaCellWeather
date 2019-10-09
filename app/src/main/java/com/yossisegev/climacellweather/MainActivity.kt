package com.kinecosystem.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.yossisegev.climacellweather.CountryAdapter
import com.yossisegev.climacellweather.ForecastAdapter
import com.yossisegev.climacellweather.CountryAdapterCallback
import com.yossisegev.climacellweather.WeatherActivity
import com.yossisegev.climacellweather.country.CapitalsVMFactory
import com.yossisegev.climacellweather.country.CapitalsViewModel
import com.yossisegev.climacellweather.country.data.CountryRepository
import com.yossisegev.climacellweather.country.entities.Country
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), CountryAdapterCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countryRepo: CountryRepository by inject()

        val capitalsViewModel = ViewModelProviders.of(this, CapitalsVMFactory(countryRepo))
            .get(CapitalsViewModel::class.java)

        capitalsViewModel.countriesData.observe(this, Observer {
            country_list.layoutManager = LinearLayoutManager(this)
            country_list.adapter = CountryAdapter(it, this)
        })

        capitalsViewModel.getCountries()
    }

    override fun onCountrySelected(country: Country) {
        Log.d("testing", "clicked on ${country.name}")
        startActivity(Intent(this, WeatherActivity::class.java))
    }
}
