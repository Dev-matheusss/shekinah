package com.example.shekinah.di

import com.example.shekinah.data.api.ApiImpl
import com.example.shekinah.data.api.ApiService
import com.example.shekinah.data.datasource.DataSource
import com.example.shekinah.data.datasource.DataSourceImpl
import com.example.shekinah.data.repository.Repository
import com.example.shekinah.data.repository.RepositoryImpl
import com.example.shekinah.domain.ShekinahUseCase
import com.example.shekinah.domain.ShekinahUseCaseImpl
import com.example.shekinah.presentation.screen.listprayscreen.ListPrayViewModel
import com.example.shekinah.presentation.screen.loginscreen.LoginViewModel
import com.example.shekinah.presentation.screen.praydetailsscreen.PrayDetailsViewModel
import com.example.shekinah.presentation.screen.registerscreen.RegisterViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val shekinahModule = module {
    singleOf(::ApiImpl) {bind<ApiService>() }
    singleOf(::DataSourceImpl) { bind<DataSource>() }
    singleOf(::RepositoryImpl) { bind<Repository>() }
    singleOf(::ShekinahUseCaseImpl) { bind<ShekinahUseCase>() }
    viewModelOf(::ListPrayViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::PrayDetailsViewModel)
    viewModelOf(::RegisterViewModel)

}