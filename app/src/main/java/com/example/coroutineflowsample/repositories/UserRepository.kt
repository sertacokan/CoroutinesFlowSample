package com.example.coroutineflowsample.repositories

import com.example.coroutineflowsample.database.UserDao
import com.example.coroutineflowsample.database.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val userList: Flow<List<UserEntity>>
        get() = userDao.getUserList()


    suspend fun deleteItem(userEntity: UserEntity?) {
        userDao.deleteUser(userEntity)
    }

    suspend fun updateItem(userEntity: UserEntity?) {
        userDao.updateUser(userEntity)
    }
}