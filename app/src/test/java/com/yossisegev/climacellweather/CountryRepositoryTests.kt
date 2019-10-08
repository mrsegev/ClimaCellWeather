package com.yossisegev.climacellweather

import com.yossisegev.climacellweather.country.*
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class CountryRepositoryTests {

    private val countryList = arrayListOf<Country>()
    private lateinit var countryRepository: CountryRepository
    private lateinit var inMemCountryDataSource: InMemCountryDataSource
    private lateinit var apiCountryDataSource: ApiCountryDataSource
    private lateinit var countryListApi: CountryListApi

    @Before
    fun before() {

        countryList.add(Country("name1", "cap1", "flag"))
        countryList.add(Country("name2", "cap2", "flag"))
        countryList.add(Country("name3", "cap3", "flag"))

        countryListApi = mock(CountryListApi::class.java)
        `when`(countryListApi.getCountryList()).thenReturn(Observable.just(countryList))
        apiCountryDataSource =
            ApiCountryDataSource(countryListApi)
        inMemCountryDataSource = InMemCountryDataSource()
        countryRepository = CountryRepository(
            inMemCountryDataSource,
            apiCountryDataSource
        )
    }

    @After
    fun after() {
        countryList.clear()
    }

    @Test
    fun get_from_api() {
        inMemCountryDataSource.getCountryList().test().assertValue {
            it.isEmpty()
        }
        countryRepository.getCountryList().test().assertValue {
            it == countryList
        }
        verify(countryListApi).getCountryList()
    }

    @Test
    fun save_to_memory() {
        inMemCountryDataSource.getCountryList().test().assertValue {
            it.isEmpty()
        }
        countryRepository.getCountryList().test()
        inMemCountryDataSource.getCountryList().test().assertValue {
            it.isNotEmpty()
        }
    }

    @Test
    fun get_from_memory() {
        inMemCountryDataSource.saveCountryList(countryList)
        countryRepository.getCountryList().test().assertValue {
            it == countryList
        }
        verify(countryListApi, never()).getCountryList()
    }

    @Test
    fun get_capitals() {
        countryRepository.getCapitalList().test().assertValue {
            it[0] == countryList[0].capital &&
                    it[1] == countryList[1].capital &&
                    it[2] == countryList[2].capital
        }
    }
}