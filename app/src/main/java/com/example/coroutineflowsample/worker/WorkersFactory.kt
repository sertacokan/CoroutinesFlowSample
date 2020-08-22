package com.example.coroutineflowsample.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.coroutineflowsample.database.UserDao
import com.example.coroutineflowsample.network.UserService

class WorkersFactory(
    private val userService: UserService,
    private val userDao: UserDao
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return UserSyncWorker(appContext, workerParameters, userService, userDao)
    }
}