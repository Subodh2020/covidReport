package com.covidreport.data.local

import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val DATABASE = "DATABASE"

val CovidDBModule = module {

    single(named(DATABASE)) {
        AppDatabase.buildDatabase(androidContext())
    }

    factory { (get(named(DATABASE)) as AppDatabase).covidReportDao() }
}