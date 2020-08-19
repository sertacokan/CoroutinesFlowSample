package com.example.coroutineflowsample.utils

import com.example.coroutineflowsample.database.UserEntity
import com.example.coroutineflowsample.models.UserResponseModel

fun List<UserResponseModel>.toEntity(): List<UserEntity> {
    return map { response ->
        UserEntity(
            id = response.id,
            name = response.name,
            surname = response.username,
            email = response.email,
            phone = response.phone,
            website = response.website
        )
    }

}
