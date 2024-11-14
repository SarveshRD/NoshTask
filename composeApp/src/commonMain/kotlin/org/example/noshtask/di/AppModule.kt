package org.example.noshtask.di

import com.connectup.company.di.repositoryModule
import com.connectup.company.di.viewModelModule

fun appModule() = listOf(platformModule(), repositoryModule, viewModelModule)