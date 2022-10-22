package com.example.cocktailsapp.koin

import com.example.cocktailsapp.ViewModel
import org.koin.dsl.module

val appModules = module {
    single {
        ViewModel()
    }
}