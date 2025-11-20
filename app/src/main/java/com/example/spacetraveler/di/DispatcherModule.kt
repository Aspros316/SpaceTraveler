package com.example.spacetraveler.di

import com.example.spacetraveler.utils.AppDispatchers
import com.example.spacetraveler.utils.DispatcherProvider
import org.koin.dsl.module

val dispatcherModule = module {
    single<DispatcherProvider> {
        AppDispatchers()
    }
}