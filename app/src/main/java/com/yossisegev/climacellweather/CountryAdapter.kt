package com.yossisegev.climacellweather

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.bumptech.glide.Glide
import com.kinecosystem.myapplication.R
import com.yossisegev.climacellweather.country.entities.Country
import kotlinx.android.synthetic.main.country_row.view.*


class CountryAdapter(private val countries: List<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_row, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int {
        return countries.size
    }



    class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(country: Country) {
            Log.d("testing", country.name)

            itemView.country_row_capital.text = country.capital
            itemView.country_row_country.text = country.name
        }
    }
}