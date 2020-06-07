package com.covidreport.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.covidreport.R
import com.covidreport.data.remote.Resource
import com.covidreport.utils.gone
import com.covidreport.utils.isConnected
import com.covidreport.utils.show
import com.covidreport.view_model.CovidViewModel
import kotlinx.android.synthetic.main.activity_covid_report.*
import org.koin.android.ext.android.inject

class CovidReportActivity : AppCompatActivity() {

    private val covidViewModel: CovidViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_report)
        callCovidViewModel()

        swipeLayoutCovid.setOnRefreshListener {
            getUpdatedCovidReport()
            swipeLayoutCovid.isRefreshing = false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun callCovidViewModel() {
        revCovidList.layoutManager = LinearLayoutManager(this)
        val covidAdapter = CovidReportAdapter(this)
        revCovidList.adapter = covidAdapter
        revCovidList.isNestedScrollingEnabled = true

        covidViewModel.covidReportList.observe(
            this,
            Observer { covidReportListResource ->
                when (covidReportListResource.status) {
                    Resource.Status.LOADING -> {
                        pbCovidReport.show()
                        txtErrorCase.gone()
                        revCovidList.gone()
                        covidReportListResource.data?.let {
                            if (it.isNotEmpty()) {
                                covidAdapter.updateItems(it)
                                pbCovidReport.gone()
                                txtErrorCase.gone()
                                revCovidList.show()
                            }
                        }
                        Log.i("CovidReportActivity:", "covidReportListResource")
                    }
                    Resource.Status.ERROR -> {
                        pbCovidReport.gone()
                        txtErrorCase.show()
                        Log.e("TAG()", "error_covidReportListResource")
                        if (isConnected()) {
                            txtErrorCase.text = "Server is not responding:${covidReportListResource.error}"
                        } else {
                            txtErrorCase.text = getString(R.string.no_internet_connection)
                        }
                    }
                    Resource.Status.SUCCESS -> {
                        pbCovidReport.gone()
                        Log.i("TAG()",
                            "SUCCESS_covidReportListResource${covidReportListResource.data}"
                        )
                        covidReportListResource.data?.let {
                            if (it.isNotEmpty()) {
                                covidAdapter.updateItems(it)
                                revCovidList.show()
                            }else{
                                txtErrorCase.show()
                                revCovidList.gone()
                                txtErrorCase.text = getString(R.string.blanck_list)
                            }
                        }

                    }
                }
            })
    }

    private fun getUpdatedCovidReport(){
        if (isConnected()) {
            covidViewModel.loadLeadActivitiesList(true)
        } else {
            covidViewModel.loadLeadActivitiesList(false)
        }
    }

    override fun onResume() {
        super.onResume()
        getUpdatedCovidReport()
    }
}