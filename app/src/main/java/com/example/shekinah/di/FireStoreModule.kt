package com.example.shekinah.di


import com.example.shekinah.data.api.firestore.FireStore
import com.example.shekinah.data.datasource.firestoredatasource.DataSourceDb
import com.example.shekinah.data.repository.database.RepositoryDb
import com.google.firebase.firestore.FirebaseFirestore
import com.example.shekinah.data.api.firestore.FireStoreImpl
import com.example.shekinah.data.datasource.firestoredatasource.DataSourceDbImpl
import com.example.shekinah.data.repository.database.RepositoryDbImpl
import com.example.shekinah.domain.usecase.database.DbUseCase
import com.example.shekinah.domain.usecase.database.DbUseCaseImpl
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
