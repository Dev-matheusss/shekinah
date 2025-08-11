package com.example.shekinah.di

import com.example.shekinah.data.api.ApiImpl
import com.example.shekinah.data.api.ApiService
import com.example.shekinah.data.datasource.DataSourceAuth
import com.example.shekinah.data.datasource.DataSourceAuthImpl
import com.example.shekinah.data.repository.RepositoryAuth
import com.example.shekinah.data.repository.RepositoryAuthImpl
import com.example.shekinah.domain.AuthUseCase
import com.example.shekinah.domain.AuthUseCaseImpl
import com.example.shekinah.presentation.screen.listprayscreen.ListPrayViewModel
import com.example.shekinah.presentation.screen.loginscreen.viewmodel.LoginViewModel
import com.example.shekinah.presentation.screen.praydetailsscreen.PrayDetailsViewModel
import com.example.shekinah.presentation.screen.registerscreen.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val shekinahModule = module {
    factory{ FirebaseAuth.getInstance() }
    singleOf(::ApiImpl) {bind<ApiService>() }
    singleOf(::DataSourceAuthImpl) { bind<DataSourceAuth>() }
    singleOf(::RepositoryAuthImpl) { bind<RepositoryAuth>() }
    singleOf(::AuthUseCaseImpl) { bind<AuthUseCase>() }
    viewModelOf(::ListPrayViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::PrayDetailsViewModel)
    viewModelOf(::RegisterViewModel)

}