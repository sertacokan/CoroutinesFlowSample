package com.example.coroutineflowsample.models

sealed class StatusModel<out T : Any>

object Loading : StatusModel<Nothing>()
object Completed : StatusModel<Nothing>()
data class Success<out T : Any>(val data: T) : StatusModel<T>()
data class Error(val msg: String?) : StatusModel<Nothing>()