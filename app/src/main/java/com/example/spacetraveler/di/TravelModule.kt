package com.example.spacetraveler.di

import org.koin.dsl.module

var travelModule = module {
    includes(
        viewModelModule,
        dispatcherModule,
        networkModule,
        useCaseModule,
        repositoryModule,
        cacheModule
    )
}