package com.covidreport.data

import androidx.lifecycle.LiveData
import com.covidreport.data.local.CovidReportDao
import com.covidreport.data.remote.CovidApiServiceImpl
import com.covidreport.data.remote.NetworkBoundResource
import com.covidreport.data.remote.Resource
import com.covidreport.model.CovidEntityModel
import com.covidreport.model.CovidEntityWrapper

interface CovidRepository {

    suspend fun getCovidList(forceRefresh: Boolean = true) : LiveData<Resource<List<CovidEntityModel>>>
}

class CovidRepositoryModuleImpl(
    private val covidApiServiceImpl: CovidApiServiceImpl,
    private val covidReportDao: CovidReportDao
) : CovidRepository{
    override suspend fun getCovidList(forceRefresh: Boolean): LiveData<Resource<List<CovidEntityModel>>> {
        return object : NetworkBoundResource<List<CovidEntityModel>, CovidEntityWrapper>() {

            override fun processResponse(response: CovidEntityWrapper): List<CovidEntityModel> {
                val covidReportList = arrayListOf<CovidEntityModel>()
                response.stateWise.forEach {
                    covidReportList.addAll(listOf(it))
                }
                return covidReportList.toList()
            }

            override suspend fun saveCallResults(items: List<CovidEntityModel>) {
                covidReportDao.insertCovidReports(items)
            }

            override fun shouldFetch(data: List<CovidEntityModel>?): Boolean =
                data == null || forceRefresh

            override suspend fun loadFromDb(): List<CovidEntityModel> =
                covidReportDao.getCovidReportList()

            override suspend fun createCallAsync(): CovidEntityWrapper =
                covidApiServiceImpl.getCovidList()


        }.build().asLiveData()
    }
}