package com.yossisegev.climacellweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kinecosystem.myapplication.R
import com.yossisegev.climacellweather.country.entities.Country
import com.yossisegev.climacellweather.weather.entities.SimpleTemp
import kotlinx.android.synthetic.main.country_row.view.*


class ForecastAdapter(private val temperatures: List<SimpleTemp>) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class ForecastViewHolder : RecyclerView.ViewHolder() {

    }
}

