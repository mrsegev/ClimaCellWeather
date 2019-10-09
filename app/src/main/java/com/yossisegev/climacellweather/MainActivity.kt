package com.yossisegev.climacellweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinecosystem.myapplication.R
import com.yossisegev.climacellweather.country.CountryVMFactory
import com.yossisegev.climacellweather.country.CountryViewModel
import com.yossisegev.climacellweather.country.data.CountryRepository
import com.yossisegev.climacellweather.country.entities.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), CountryAdapterCallback, TextWatcher {

    private lateinit var searchSubject: PublishSubject<String>
    private lateinit var countriesViewModel: CountryViewModel
    private var adapter: CountryAdapter? = null
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSearchSubject()

        val countryRepository: CountryRepository by inject()
        countriesViewModel = ViewModelProviders.of(
            this,
            CountryVMFactory(countryRepository))
            .get(CountryViewModel::class.java)

        countriesViewModel.countriesData.observe(this, Observer { countries ->
            country_list.layoutManager = LinearLayoutManager(this)

            adapter?.let { // Searching
                adapter!!.setCountries(countries)

            }?: run { // First time fetch
                adapter = CountryAdapter(countries, this)
                country_list.adapter = adapter
                search_edit_text.isEnabled = true
                search_edit_text.addTextChangedListener(this)
            }
        })

        countriesViewModel.getCountries()
    }

    override fun onCountrySelected(country: Country) {
        startActivity(Intent(this, WeatherActivity::class.java))
    }

    override fun afterTextChanged(string: Editable?) {}

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

    override fun onTextChanged(string: CharSequence?, p1: Int, p2: Int, p3: Int) {
        searchSubject.onNext(string.toString())
    }

    private fun initSearchSubject() {
        searchSubject = PublishSubject.create()
        val disposable = searchSubject.debounce(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                countriesViewModel.search(it)
            }

        disposables.add(disposable)
    }
}
