package com.covidreport.data.remote

import com.covidreport.model.CovidEntityModel
import com.covidreport.model.CovidEntityWrapper
import retrofit2.http.GET

interface CovidApiService {

@GET("data.json")
suspend fun loadCovidList(): CovidEntityWrapper

}