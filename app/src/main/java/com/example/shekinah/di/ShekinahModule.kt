package com.example.shekinah.di

import com.example.shekinah.data.api.ApiImpl
import com.example.shekinah.data.api.ApiService
import com.example.shekinah.data.datasource.fireauth.AuthDataSource
import com.example.shekinah.data.datasource.fireauth.AuthDataSourceImpl
import com.example.shekinah.data.repository.firebaseauth.AuthRepository
import com.example.shekinah.data.repository.firebaseauth.AuthRepositoryImpl
import com.example.shekinah.domain.usecase.firebaseauth.AuthUseCase
import com.example.shekinah.domain.usecase.firebaseauth.AuthUseCaseImpl
import com.example.shekinah.presentation.screen.listprays.viewModel.ListPrayViewModel
import com.example.shekinah.presentation.screen.login.viewmodel.LoginViewModel
import com.example.shekinah.presentation.screen.placeorder.PlaceOrderViewModel
import com.example.shekinah.presentation.screen.praydetails.PrayDetailsViewModel
import com.example.shekinah.presentation.screen.profile.ProfileViewModel
import com.example.shekinah.presentation.screen.recoverPassword.RecoverPasswordViewModel
import com.example.shekinah.presentation.screen.register.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val shekinahModule = module {
    factory{ FirebaseAuth.getInstance() }
    singleOf(::ApiImpl) {bind<ApiService>() }
    singleOf(::AuthDataSourceImpl) { bind<AuthDataSource>() }
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    singleOf(::AuthUseCaseImpl) { bind<AuthUseCase>() }
    viewModelOf(::ListPrayViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::PrayDetailsViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::PlaceOrderViewModel)
    viewModelOf(::RecoverPasswordViewModel)
    viewModelOf(::ProfileViewModel)

}