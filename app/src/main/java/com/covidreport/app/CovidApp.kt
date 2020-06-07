package com.covidreport.app

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import com.covidreport.data.local.CovidDBModule
import com.covidreport.data.remote.CovidRepositoryModule
import com.covidreport.data.remote.CovidViewModelModule
import com.covidreport.data.remote.createRemoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CovidApp : MultiDexApplication() {

    init {
        instance = this

    }

    companion object {
        private var instance: CovidApp? = null
        private const val BASE_URL = "https://api.covid19india.org/"
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()

            androidContext(this@CovidApp)

            modules(
                listOf(createRemoteModule(BASE_URL, BuildConfig.DEBUG), CovidDBModule, CovidRepositoryModule, CovidViewModelModule)
            )
        }
    }
}