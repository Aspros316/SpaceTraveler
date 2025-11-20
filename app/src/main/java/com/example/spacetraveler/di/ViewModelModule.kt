package com.example.spacetraveler.di

import com.example.spacetraveler.presentation.SpaceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SpaceViewModel(get(), get())
    }
}