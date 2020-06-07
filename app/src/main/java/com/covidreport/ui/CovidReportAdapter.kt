package com.covidreport.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.covidreport.R
import com.covidreport.model.CovidEntityModel
import kotlinx.android.synthetic.main.item_layout_covid_report.view.*

class CovidReportAdapter(private val context: Context) : RecyclerView.Adapter<CovidReportAdapter.CovidViewHolder>() {

    private var covidReportLists: ArrayList<CovidEntityModel> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CovidReportAdapter.CovidViewHolder {
        return CovidViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_layout_covid_report, parent, false)
        )
    }

    override fun getItemCount(): Int = covidReportLists.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CovidReportAdapter.CovidViewHolder, position: Int) {
        val items = covidReportLists[position]
        holder.txtState.text = "State Name: ${items.state}"
        holder.txtTotalCases.text = "Total Cases: "+items.confirmed
        holder.txtActiveCases.text = "Total Active: "+items.active
        holder.txtRecovered.text = "Total Recovered: "+items.recovered
        holder.txtDeath.text = "Total Death: "+items.deaths
        holder.updtatedOn.text = "Updated On: "+items.lastUpdatedTime
    }

    inner class CovidViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtState: TextView = view.txtState
        val txtTotalCases: TextView = view.txtTotalCase
        val txtActiveCases: TextView = view.txtActiveCase
        val txtRecovered: TextView = view.txtRecoverCase
        val txtDeath: TextView = view.txtDeathCase
        val updtatedOn = view.txtUpdatedOn
    }

    fun updateItems(covidReportList: List<CovidEntityModel>) {
        this.covidReportLists.clear()
        this.covidReportLists.addAll(covidReportList)
        notifyDataSetChanged()
    }
}