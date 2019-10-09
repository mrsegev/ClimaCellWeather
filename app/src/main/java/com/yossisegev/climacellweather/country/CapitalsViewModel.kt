package com.yossisegev.climacellweather.country

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yossisegev.climacellweather.country.data.CountryRepository
import com.yossisegev.climacellweather.country.entities.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CapitalsViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    val countriesData = MutableLiveData<List<Country>>()
    private val disposables = CompositeDisposable()

    fun getCountries() {
        val disposable = countryRepository.getCountryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ countries ->
                countriesData.postValue(countries)
            }, { err ->
                Log.e("Testing", err.localizedMessage)
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