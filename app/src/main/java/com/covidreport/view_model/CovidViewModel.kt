package com.covidreport.view_model

import androidx.lifecycle.*
import com.covidreport.data.CovidRepository
import com.covidreport.data.remote.AppDispatchers
import com.covidreport.data.remote.Resource
import com.covidreport.model.CovidEntityModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CovidViewModel(private val covidRepository: CovidRepository,
                     private val dispatchers: AppDispatchers) : ViewModel()  {

    private val _covidReportList = MediatorLiveData<Resource<List<CovidEntityModel>>>()

    val covidReportList: LiveData<Resource<List<CovidEntityModel>>> get() = _covidReportList

    private var covidReportListSource: LiveData<Resource<List<CovidEntityModel>>> = MutableLiveData()

    fun loadLeadActivitiesList(forceRefresh: Boolean) = getCovidReportList(forceRefresh)

    private fun getCovidReportList(forceRefresh: Boolean) = viewModelScope.launch(dispatchers.main) {
        _covidReportList.removeSource(covidReportListSource)

        withContext(dispatchers.io) {
            covidReportListSource = covidRepository.getCovidList(forceRefresh)
        }
        _covidReportList.addSource(covidReportListSource) {
            _covidReportList.value = it

        }
    }
}