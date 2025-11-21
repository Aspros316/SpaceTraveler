package com.example.spacetraveler.di

import com.example.spacetraveler.presentation.TravelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        TravelViewModel(get(), get(), get(), get())
    }
}