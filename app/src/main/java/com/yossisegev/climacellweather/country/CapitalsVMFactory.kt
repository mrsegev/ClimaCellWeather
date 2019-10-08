package com.yossisegev.climacellweather.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yossisegev.climacellweather.country.data.CountryRepository

@Suppress("UNCHECKED_CAST")
class CapitalsVMFactory(private val countryRepository: CountryRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CapitalsViewModel(countryRepository) as T
    }

}