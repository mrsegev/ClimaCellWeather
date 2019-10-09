package com.yossisegev.climacellweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kinecosystem.myapplication.R
import com.yossisegev.climacellweather.country.entities.Country
import kotlinx.android.synthetic.main.country_row.view.*


class CountryAdapter(private var countries: List<Country>, private val callback: CountryAdapterCallback) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    fun setCountries(countries: List<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_row, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position], callback)
    }

    override fun getItemCount(): Int {
        return countries.size
    }


    class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(country: Country, callback: CountryAdapterCallback?) = with(itemView) {
            country_row_capital.text = country.capital
            country_row_country.text = country.name
            setOnClickListener { callback?.onCountrySelected(country) }
        }
    }
}

interface CountryAdapterCallback {
    fun onCountrySelected(country: Country)
}