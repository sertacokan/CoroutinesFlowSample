package com.example.coroutineflowsample.repositories

import com.example.coroutineflowsample.database.UserDao
import com.example.coroutineflowsample.database.UserEntity
import com.example.coroutineflowsample.network.UserService
import com.example.coroutineflowsample.utils.toEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userService: UserService, private val userDao: UserDao) {

    val userList: Flow<List<UserEntity>>
        get() = userDao.getUserList()

    suspend fun getUserList() {
        val users = userService.userList()
        userDao.addUsers(users = users.toEntity())
    }
}