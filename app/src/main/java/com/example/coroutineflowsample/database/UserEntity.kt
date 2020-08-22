package com.example.coroutineflowsample.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val phone: String,
    val website: String
)