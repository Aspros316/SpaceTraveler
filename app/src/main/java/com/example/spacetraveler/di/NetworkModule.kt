package com.example.spacetraveler.di

import com.example.spacetraveler.data.repository.retrofit.SpaceWebService
import com.example.spacetraveler.utils.NetworkHandler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

// Retrofit
    single {
        Retrofit.Builder()
            .baseUrl("http://192.168.50.114:3001/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Servicios
    single { get<Retrofit>().create(SpaceWebService::class.java) }


    single { NetworkHandler(get()) }   // get() = Context

}