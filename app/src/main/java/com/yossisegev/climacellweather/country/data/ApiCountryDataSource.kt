package com.yossisegev.climacellweather.country.data

import com.yossisegev.climacellweather.country.entities.Country
import io.reactivex.Observable

class ApiCountryDataSource(val countryListApi: CountryListApi) :
    CountryDataSource {


    override fun getCapitalList(): Observable<ArrayList<String>> {
        return getCountryList().map {
            ArrayList(it.map { country -> country.capital })
        }
    }

    override fun getCountryList(): Observable<ArrayList<Country>> {
        return countryListApi.getCountryList()

    }

}