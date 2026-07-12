package com.example.shekinah.di

import com.example.shekinah.data.api.ApiImpl
import com.example.shekinah.data.api.ApiService
import com.example.shekinah.data.datasource.fireauth.AuthDataSource
import com.example.shekinah.data.datasource.fireauth.AuthDataSourceImpl
import com.example.shekinah.data.datasource.firestore.DbDataSource
import com.example.shekinah.data.datasource.firestore.DbDataSourceImpl
import com.example.shekinah.data.datasource.storage.StorageDataSource
import com.example.shekinah.data.datasource.storage.StorageDataSourceImpl
import com.example.shekinah.data.repository.firebaseauth.AuthRepository
import com.example.shekinah.data.repository.firebaseauth.AuthRepositoryImpl
import com.example.shekinah.data.repository.firestore.FirestoreRepository
import com.example.shekinah.data.repository.firestore.FirestoreRepositoryImpl
import com.example.shekinah.data.repository.storage.StorageRepository
import com.example.shekinah.data.repository.storage.StorageRepositoryImpl
import com.example.shekinah.domain.usecase.firestore.FirestoreUseCase
import com.example.shekinah.domain.usecase.firestore.FirestoreUseCaseImpl
import com.example.shekinah.domain.usecase.firebaseauth.AuthUseCase
import com.example.shekinah.domain.usecase.firebaseauth.AuthUseCaseImpl
import com.example.shekinah.domain.usecase.storage.StorageUseCase
import com.example.shekinah.domain.usecase.storage.StorageUseCaseImpl
import com.example.shekinah.presentation.screen.listprays.viewmodel.ListPrayViewModel
import com.example.shekinah.presentation.screen.login.viewmodel.LoginViewModel
import com.example.shekinah.presentation.screen.placeorder.viewmodel.PlaceOrderViewModel
import com.example.shekinah.presentation.screen.praydetails.viewmodel.PrayDetailsViewModel
import com.example.shekinah.presentation.screen.profile.viewmodel.ProfileViewModel
import com.example.shekinah.presentation.screen.recoverpassword.RecoverPasswordViewModel
import com.example.shekinah.presentation.screen.register.viewmodel.RegisterViewModel
import com.example.shekinah.presentation.screen.warnings.viewmodel.CreateWarningViewModel
import com.example.shekinah.presentation.screen.warnings.viewmodel.ListWarningsViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val shekinahModule = module {
    factory{ FirebaseAuth.getInstance() }
    factory{FirebaseFirestore.getInstance()}
    factory{ FirebaseStorage.getInstance() }
    singleOf(::ApiImpl) {bind<ApiService>() }
    singleOf(::AuthDataSourceImpl) { bind<AuthDataSource>() }
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    singleOf(::AuthUseCaseImpl) { bind<AuthUseCase>() }

    singleOf(::DbDataSourceImpl) { bind<DbDataSource>() }
    singleOf(::FirestoreRepositoryImpl) { bind<FirestoreRepository>() }
    singleOf(::FirestoreUseCaseImpl) { bind<FirestoreUseCase>() }
    //storage
    singleOf(::StorageDataSourceImpl) { bind<StorageDataSource>() }
    singleOf(::StorageRepositoryImpl) { bind<StorageRepository>() }
    singleOf(::StorageUseCaseImpl) { bind<StorageUseCase>() }
    //viewmodel
    viewModelOf(::ListPrayViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::PrayDetailsViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::PlaceOrderViewModel)
    viewModelOf(::RecoverPasswordViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::ListWarningsViewModel)
    viewModelOf(:: CreateWarningViewModel)

}