package com.covidreport.data.remote

import com.covidreport.data.CovidRepository
import com.covidreport.data.CovidRepositoryModuleImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val CovidRepositoryModule = module {

    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }

    factory {
        CovidRepositoryModuleImpl(
            get(),
            get()) as CovidRepository
    }
}