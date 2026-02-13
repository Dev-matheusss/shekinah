package com.example.shekinah.di


import com.example.shekinah.data.datasource.firestore.DbDataSource
import com.example.shekinah.data.datasource.firestore.DbDataSourceImpl
import com.example.shekinah.data.datasource.storage.StorageDataSource
import com.example.shekinah.data.datasource.storage.StorageDataSourceImpl
import com.example.shekinah.data.repository.firestore.FirestoreRepository
import com.example.shekinah.data.repository.firestore.FirestoreRepositoryImpl
import com.example.shekinah.data.repository.storage.StorageRepository
import com.example.shekinah.data.repository.storage.StorageRepositoryImpl
import com.example.shekinah.domain.usecase.database.FirestoreUseCase
import com.example.shekinah.domain.usecase.database.FirestoreUseCaseImpl
import com.example.shekinah.domain.usecase.storage.StorageUseCase
import com.example.shekinah.domain.usecase.storage.StorageUseCaseImpl
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val firestoreModule = module{
   factory{FirebaseFirestore.getInstance()}
   factory{ FirebaseStorage.getInstance() }
    singleOf(::DbDataSourceImpl) { bind<DbDataSource>() }
    singleOf(::FirestoreRepositoryImpl) { bind<FirestoreRepository>() }
    singleOf(::FirestoreUseCaseImpl) { bind<FirestoreUseCase>() }
    //storage
    singleOf(::StorageDataSourceImpl) { bind<StorageDataSource>() }
    singleOf(::StorageRepositoryImpl) { bind<StorageRepository>() }
    singleOf(::StorageUseCaseImpl) { bind<StorageUseCase>() }

}
