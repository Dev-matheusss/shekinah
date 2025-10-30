package com.example.shekinah.di

import com.example.shekinah.data.api.fireauth.ApiImpl
import com.example.shekinah.data.api.fireauth.ApiService
import com.example.shekinah.data.datasource.fireauthdatasource.DataSourceAuth
import com.example.shekinah.data.datasource.fireauthdatasource.DataSourceAuthImpl
import com.example.shekinah.data.repository.firebaseauth.RepositoryAuth
import com.example.shekinah.data.repository.firebaseauth.RepositoryAuthImpl
import com.example.shekinah.domain.usecase.firebaseauth.AuthUseCase
import com.example.shekinah.domain.usecase.firebaseauth.AuthUseCaseImpl
import com.example.shekinah.presentation.screen.listprayscreen.ListPrayViewModel
import com.example.shekinah.presentation.screen.loginscreen.viewmodel.LoginViewModel
import com.example.shekinah.presentation.screen.placeorder.PlaceOrderViewModel
import com.example.shekinah.presentation.screen.praydetailsscreen.PrayDetailsViewModel
import com.example.shekinah.presentation.screen.recoverPassword.RecoverPasswordViewModel
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
    viewModelOf(::PlaceOrderViewModel)
    viewModelOf(::RecoverPasswordViewModel)

}