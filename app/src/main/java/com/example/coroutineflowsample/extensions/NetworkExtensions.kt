package com.example.coroutineflowsample.extensions

import androidx.lifecycle.liveData
import com.example.coroutineflowsample.models.Completed
import com.example.coroutineflowsample.models.Error
import com.example.coroutineflowsample.models.Loading
import com.example.coroutineflowsample.models.Success
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
fun <T : Any> Flow<T>.asStatusLiveData() = liveData {
    onStart {
        emit(Loading)
        delay(5000)
    }.catch { error ->
        emit(Error(error.localizedMessage))
    }.onCompletion {
        emit(Completed)
    }.collect { result ->
        emit(Success(result))
    }
}