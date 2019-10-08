package com.yossisegev.climacellweather.country

import com.yossisegev.climacellweather.country.Country
import io.reactivex.Observable
import retrofit2.http.GET

interface CountryListApi {

    @GET("all")
    fun getCountryList(): Observable<ArrayList<Country>>
}