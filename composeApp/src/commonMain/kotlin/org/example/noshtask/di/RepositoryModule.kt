package com.connectup.company.di


import org.example.noshtask.data.repository.CookRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<CookRepository> { CookRepository(get()) }


}