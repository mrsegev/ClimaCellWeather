package com.yossisegev.climacellweather.country

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yossisegev.climacellweather.country.data.CountryRepository
import com.yossisegev.climacellweather.country.entities.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CapitalsViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    val countriesData = MutableLiveData<List<Country>>()

    //TODO: clear!!
    fun getCountries() {
        countryRepository.getCountryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ countries ->
                countriesData.postValue(countries)
            }, { err ->
                Log.e("testing", err.localizedMessage)
            })
    }

    override fun onCleared() {
        super.onCleared()
    }
}