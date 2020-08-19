package com.example.coroutineflowsample.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.coroutineflowsample.adapters.UserListAdapter
import com.example.coroutineflowsample.database.UserEntity
import com.example.coroutineflowsample.models.Loading
import com.example.coroutineflowsample.models.StatusModel
import com.example.coroutineflowsample.models.Success

@BindingAdapter("data")
fun RecyclerView.setData(status: StatusModel<List<UserEntity>>) {
    when (status) {
        is Success -> (adapter as UserListAdapter).submitList(status.data)
    }
}

@BindingAdapter("status")
fun LottieAnimationView.setStatus(status: StatusModel<List<UserEntity>>) {
    when (status) {
        is Loading -> playAnimation()
        else -> {
            cancelAnimation()
            visibility = View.GONE
        }
    }
}