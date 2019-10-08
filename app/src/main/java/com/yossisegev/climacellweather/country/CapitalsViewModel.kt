package com.yossisegev.climacellweather.country

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CapitalsViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    val capitalsData = MutableLiveData<List<String>>()

    fun getCapitals() {

        countryRepository.getCapitalList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ capitals ->
                capitalsData.postValue(capitals)
            }, { err ->
                Log.e("testing", err.localizedMessage)
            })

    }

    override fun onCleared() {
        super.onCleared()
    }
}