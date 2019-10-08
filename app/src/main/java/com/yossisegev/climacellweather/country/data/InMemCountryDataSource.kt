package com.yossisegev.climacellweather.country.data

import com.yossisegev.climacellweather.country.entities.Country
import io.reactivex.Observable
import kotlin.collections.ArrayList

class InMemCountryDataSource : CountryDataSource {

    private var countryList = arrayListOf<Country>()

    fun saveCountryList(countryList: ArrayList<Country>) {
        this.countryList = countryList
    }

    override fun getCapitalList(): Observable<ArrayList<String>> {
        return Observable.fromCallable {
            ArrayList(countryList.map { it.capital })
        }
    }

    override fun getCountryList(): Observable<ArrayList<Country>> {
        return Observable.just(countryList)
    }

}