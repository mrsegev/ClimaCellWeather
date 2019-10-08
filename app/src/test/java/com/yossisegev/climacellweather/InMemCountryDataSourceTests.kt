package com.yossisegev.climacellweather

import com.yossisegev.climacellweather.country.Country
import com.yossisegev.climacellweather.country.InMemCountryDataSource
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class InMemCountryDataSourceTests {

    @Test
    fun get_capitals() {

        val repo = InMemCountryDataSource()
        repo.saveCountryList(getMockCountryList())

        repo.getCapitalList().test().assertValue {
            it[0] == getMockCountryList()[0].capital &&
            it[1] == getMockCountryList()[1].capital &&
            it[2] == getMockCountryList()[2].capital
        }
    }

    @Test
    fun save_countryList() {
        val repo = InMemCountryDataSource()
        repo.saveCountryList(getMockCountryList())
        repo.getCountryList().test().assertValue {
            it.size == getMockCountryList().size
        }
    }

    private fun getMockCountryList(): ArrayList<Country> {
        val countryList = arrayListOf<Country>()
        countryList.add(Country("name1", "cap1", "flag"))
        countryList.add(Country("name2", "cap2", "flag"))
        countryList.add(Country("name3", "cap3", "flag"))
        return countryList
    }
}
