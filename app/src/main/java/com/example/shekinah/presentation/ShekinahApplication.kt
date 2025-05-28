package com.example.shekinah.presentation

import android.app.Application
import com.example.shekinah.di.shekinahModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ShekinahApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ShekinahApplication)
            modules(listOf(shekinahModule))

        }
    }
}