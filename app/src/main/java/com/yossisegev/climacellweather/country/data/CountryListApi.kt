package com.yossisegev.climacellweather.country.data

import com.yossisegev.climacellweather.country.entities.Country
import io.reactivex.Observable
import retrofit2.http.GET

interface CountryListApi {

    @GET("all")
    fun getCountryList(): Observable<ArrayList<Country>>
}