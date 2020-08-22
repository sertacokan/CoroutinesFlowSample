package com.example.coroutineflowsample.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.coroutineflowsample.database.UserDao
import com.example.coroutineflowsample.network.UserService
import com.example.coroutineflowsample.utils.toEntity

class UserSyncWorker(
    appContext: Context,
    workerParameters: WorkerParameters,
    private val userService: UserService,
    private val userDao: UserDao
) : CoroutineWorker(appContext, workerParameters) {

    companion object {
        const val WORKER_NAME = "UserSyncWorker"
    }

    //Run on the background thread
    override suspend fun doWork(): Result {
        val response = userService.userList()
        return when (response.code()) {
            200 -> {
                val users = response.body()?.toEntity() ?: emptyList()
                userDao.addUsers(users)
                Result.success()
            }
            in 500..511 -> Result.failure()
            else -> Result.retry()
        }
    }
}