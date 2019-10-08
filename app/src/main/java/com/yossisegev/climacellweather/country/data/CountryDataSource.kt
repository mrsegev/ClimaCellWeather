package com.yossisegev.climacellweather.country.data

import com.yossisegev.climacellweather.country.entities.Country
import io.reactivex.Observable

interface CountryDataSource {

    fun getCapitalList(): Observable<ArrayList<String>>
    fun getCountryList(): Observable<ArrayList<Country>>
}