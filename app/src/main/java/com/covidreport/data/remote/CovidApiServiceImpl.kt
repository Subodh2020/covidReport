package com.covidreport.data.remote

class CovidApiServiceImpl(private val covidApiService: CovidApiService){

    suspend fun getCovidList() = covidApiService.loadCovidList()
}