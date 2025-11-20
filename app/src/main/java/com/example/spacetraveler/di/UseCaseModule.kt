package com.example.spacetraveler.di

import com.example.spacetraveler.domain.CreateTravelUseCase
import org.koin.dsl.module

val useCaseModule = module {
    // Use Cases
    single { CreateTravelUseCase(get()) }

}