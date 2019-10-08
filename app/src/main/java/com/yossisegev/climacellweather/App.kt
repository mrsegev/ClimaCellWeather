package com.yossisegev.climacellweather

import android.app.Application
import com.yossisegev.climacellweather.country.data.ApiCountryDataSource
import com.yossisegev.climacellweather.country.data.CountryListApi
import com.yossisegev.climacellweather.country.data.CountryRepository
import com.yossisegev.climacellweather.country.data.InMemCountryDataSource
import com.yossisegev.climacellweather.weather.data.RequestHeaderInterceptor
import com.yossisegev.climacellweather.weather.data.WeatherApi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }


    private fun initDI() {

        val countryModule = module {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://restcountries.eu/rest/v2/")
                .build()

            factory<CountryListApi> {
                retrofit.create(CountryListApi::class.java)
            }

            single {
                ApiCountryDataSource(
                    get()
                )
            }
            single { InMemCountryDataSource() }
            single { CountryRepository(get(), get()) }
        }

        val weatherModule = module {


            val httpClient = OkHttpClient.Builder()
                .addInterceptor(
                    RequestHeaderInterceptor(
                        "mFW54hIC4r5puNkKBrcfQ3Xy3dqFYXCJ"
                    )
                )
                .build()

            val retrofit = Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.climacell.co/v3/")
                .build()

            factory<WeatherApi> { retrofit.create(WeatherApi::class.java) }
        }

        startKoin {
            androidContext(this@App)
            val modulesList = arrayListOf<Module>()
            modulesList.add(countryModule)
            modulesList.add(weatherModule)
            modules(modulesList)
        }
    }
}