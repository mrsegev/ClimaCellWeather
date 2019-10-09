package com.yossisegev.climacellweather.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yossisegev.climacellweather.country.data.CountryRepository

@Suppress("UNCHECKED_CAST")
class CountryVMFactory(private val countryRepository: CountryRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CountryViewModel(countryRepository) as T
    }

}