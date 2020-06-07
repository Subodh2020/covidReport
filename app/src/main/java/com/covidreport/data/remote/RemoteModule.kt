package com.covidreport.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun createRemoteModule(baseUrl: String, isDebug: Boolean) = module {

    factory<Interceptor> {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (isDebug)
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        return@factory loggingInterceptor
    }

    factory {
        OkHttpClient.Builder().addInterceptor(get()).build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory {
        get<Retrofit>().create(CovidApiService::class.java)
    }

    factory {
        CovidApiServiceImpl(get())
    }
}