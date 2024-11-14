package com.connectup.company.di

import org.koin.dsl.module
import presentation.viewModel.CookViewModel


val viewModelModule = module {

    single { CookViewModel(get()) }

}