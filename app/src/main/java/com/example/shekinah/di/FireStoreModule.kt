package com.example.shekinah.di


import com.example.shekinah.data.api.FireStore
import com.example.shekinah.data.datasource.DataSourceDb
import com.example.shekinah.data.repository.RepositoryDb
import com.google.firebase.firestore.FirebaseFirestore
import com.example.shekinah.data.api.FireStoreImpl
import com.example.shekinah.data.datasource.DataSourceDbImpl
import com.example.shekinah.data.repository.RepositoryDbImpl
import com.example.shekinah.domain.DbUseCase
import com.example.shekinah.domain.DbUseCaseImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.core.module.dsl.bind

val firestoreModule = module{
   factory{FirebaseFirestore.getInstance()}
    singleOf(::FireStoreImpl) { bind<FireStore>() }
    singleOf(::DataSourceDbImpl) { bind<DataSourceDb>() }
    singleOf(::RepositoryDbImpl) { bind<RepositoryDb>() }
    singleOf(::DbUseCaseImpl) { bind<DbUseCase>() }
}
