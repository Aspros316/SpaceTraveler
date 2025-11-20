package com.example.spacetraveler.di

import com.example.spacetraveler.data.repository.retrofit.SpaceWebService
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

// Retrofit
    single {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3001")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Servicios
    single { get<Retrofit>().create(SpaceWebService::class.java) }


}