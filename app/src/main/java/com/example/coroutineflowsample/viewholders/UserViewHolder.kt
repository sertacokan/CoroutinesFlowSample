package com.example.coroutineflowsample.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutineflowsample.database.UserEntity
import com.example.coroutineflowsample.databinding.ListItemUserBinding

class UserViewHolder private constructor(private val binding: ListItemUserBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createHolder(parent: ViewGroup): UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemUserBinding.inflate(layoutInflater, parent, false)
            return UserViewHolder(binding)
        }
    }

    fun bind(userEntity: UserEntity) {
        binding.run {
            user = userEntity
            executePendingBindings()
        }
    }
}