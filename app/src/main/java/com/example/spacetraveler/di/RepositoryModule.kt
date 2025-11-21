package com.example.spacetraveler.di

import com.example.spacetraveler.data.repository.TravelRepositoryImpl
import com.example.spacetraveler.data.source.TravelRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<TravelRepository> { TravelRepositoryImpl(webService = get(), cache = get(), networkHandler = get()) }
}