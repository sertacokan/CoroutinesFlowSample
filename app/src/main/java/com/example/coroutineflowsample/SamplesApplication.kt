package com.example.coroutineflowsample

import android.app.Application
import com.example.coroutineflowsample.di.databaseModule
import com.example.coroutineflowsample.di.repositoryModule
import com.example.coroutineflowsample.di.retrofitModule
import com.example.coroutineflowsample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SamplesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(retrofitModule, viewModelModule, databaseModule, repositoryModule)
        }
    }
}