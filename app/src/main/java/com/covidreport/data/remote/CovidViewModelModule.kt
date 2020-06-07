package com.covidreport.data.remote

import com.covidreport.view_model.CovidViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val CovidViewModelModule = module {

    viewModel { CovidViewModel(get(), get()) }
}