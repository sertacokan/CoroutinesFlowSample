package com.example.coroutineflowsample.network

import com.example.coroutineflowsample.models.UserResponseModel
import retrofit2.http.GET

interface UserService {

    @GET("users")
    suspend fun userList(): List<UserResponseModel>

}