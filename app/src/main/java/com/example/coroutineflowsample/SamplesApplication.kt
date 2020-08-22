package com.example.coroutineflowsample

import android.app.Application
import androidx.work.*
import com.example.coroutineflowsample.di.*
import com.example.coroutineflowsample.worker.UserSyncWorker
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

class SamplesApplication : Application(), Configuration.Provider {

    //Injects
    private val configuration by inject<Configuration>()

    override fun getWorkManagerConfiguration(): Configuration {
        return configuration
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(retrofitModule, viewModelModule, databaseModule, repositoryModule, workerModule)
        }

        scheduleUserSyncWorker()
    }

    private fun scheduleUserSyncWorker() {

        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .build()

        val workerRequest = PeriodicWorkRequestBuilder<UserSyncWorker>(7, TimeUnit.DAYS)
            .setConstraints(constraint)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            UserSyncWorker.WORKER_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            workerRequest
        )
    }
}