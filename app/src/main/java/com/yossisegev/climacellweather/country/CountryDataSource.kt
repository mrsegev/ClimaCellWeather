package com.yossisegev.climacellweather.country

import com.yossisegev.climacellweather.country.Country
import io.reactivex.Observable

interface CountryDataSource {

    fun getCapitalList(): Observable<ArrayList<String>>
    fun getCountryList(): Observable<ArrayList<Country>>
}