package com.example.coroutineflowsample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.coroutineflowsample.database.UserEntity
import com.example.coroutineflowsample.models.*
import com.example.coroutineflowsample.repositories.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    val users: LiveData<StatusModel<List<UserEntity>>> = liveData {
        userRepository.userList
            .onStart {
                emit(Loading)
                kotlinx.coroutines.delay(5000)
            }.catch { error ->
                emit(Error(error.localizedMessage))
            }
            .onCompletion {
                emit(Completed)
            }
            .collect { userList ->

                emit(Success(userList))
            }
    }

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            userRepository.getUserList()
        }

    }

}