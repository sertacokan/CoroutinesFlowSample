package com.example.coroutineflowsample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.coroutineflowsample.database.UserEntity
import com.example.coroutineflowsample.extensions.asStatusLiveData
import com.example.coroutineflowsample.models.StatusModel
import com.example.coroutineflowsample.repositories.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    @ExperimentalCoroutinesApi
    val users: LiveData<StatusModel<List<UserEntity>>> = liveData {
        emitSource(userRepository.userList.asStatusLiveData())
    }

    fun deleteItem(userEntity: UserEntity?) {
        viewModelScope.launch {
            userRepository.deleteItem(userEntity)
        }
    }

    fun updateItem(userEntity: UserEntity?) {
        viewModelScope.launch {
            userRepository.updateItem(userEntity)
        }
    }

}