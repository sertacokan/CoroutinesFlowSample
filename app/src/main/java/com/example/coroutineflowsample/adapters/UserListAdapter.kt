package com.example.coroutineflowsample.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.coroutineflowsample.database.UserEntity
import com.example.coroutineflowsample.utils.UserItemSelection
import com.example.coroutineflowsample.viewholders.UserViewHolder

class UserListAdapter(private val selection: UserItemSelection) :
    ListAdapter<UserEntity, UserViewHolder>(USER_DIFF_UTIL) {

    companion object {
        val USER_DIFF_UTIL = object : DiffUtil.ItemCallback<UserEntity>() {
            override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.createHolder(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), selection)
    }
}