package org.example.noshtask.di

import org.example.noshtask.data.factory.ApiService
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {

    single { ApiService().build() }

}