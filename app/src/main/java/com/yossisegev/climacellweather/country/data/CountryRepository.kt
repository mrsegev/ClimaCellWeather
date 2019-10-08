package com.yossisegev.climacellweather.country.data

import com.yossisegev.climacellweather.country.entities.Country
import io.reactivex.Observable


class CountryRepository(private val inMemCountryDataSource: InMemCountryDataSource,
                        private val apiCountryDataSource: ApiCountryDataSource
) : CountryDataSource {


    override fun getCapitalList(): Observable<ArrayList<String>> {
        return getCountryList().flatMap {
            Observable.just(ArrayList(it.map { country -> country.capital }))
        }
    }

    override fun getCountryList(): Observable<ArrayList<Country>> {
        return inMemCountryDataSource.getCountryList().flatMap {
            if (it.isNotEmpty()) {
                Observable.just(it)
            } else {
                apiCountryDataSource.getCountryList().doAfterNext { apiList ->
                    inMemCountryDataSource.saveCountryList(apiList)
                }
            }
        }

    }

}