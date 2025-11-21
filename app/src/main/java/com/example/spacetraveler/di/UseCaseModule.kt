package com.example.spacetraveler.di

import com.example.spacetraveler.domain.CreateTravelUseCase
import com.example.spacetraveler.domain.GetListTravelUseCase
import com.example.spacetraveler.domain.GetTravelerByIdUseCase
import org.koin.dsl.module

val useCaseModule = module {
    // Use Cases
    single { CreateTravelUseCase(get()) }

    single { GetListTravelUseCase(get()) }

    single { GetTravelerByIdUseCase(get()) }
}