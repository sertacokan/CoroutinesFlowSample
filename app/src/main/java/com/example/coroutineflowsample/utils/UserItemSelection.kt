package com.example.coroutineflowsample.utils

import com.example.coroutineflowsample.database.UserEntity

class UserItemSelection(private val selection: (UserEntity?) -> Unit) {

    fun onSelection(userEntity: UserEntity?) = selection(userEntity)
}