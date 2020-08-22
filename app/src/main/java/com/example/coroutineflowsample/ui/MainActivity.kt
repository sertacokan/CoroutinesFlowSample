package com.example.coroutineflowsample.ui

import android.os.Build
import android.os.Bundle
import android.view.ActionMode
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.coroutineflowsample.R
import com.example.coroutineflowsample.adapters.UserListAdapter
import com.example.coroutineflowsample.database.UserEntity
import com.example.coroutineflowsample.databinding.ActivityMainBinding
import com.example.coroutineflowsample.utils.ActionModeCallback
import com.example.coroutineflowsample.utils.UserItemSelection
import com.example.coroutineflowsample.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = mainViewModel
        }

        setSupportActionBar(binding.toolbar)
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val selection = UserItemSelection { userEntity -> createActionMode(userEntity) }
        binding.rvUserList.apply {
            setHasFixedSize(true)
            adapter = UserListAdapter(selection)
        }
    }

    private fun createActionMode(userEntity: UserEntity?) {
        val actionModeCallback = ActionModeCallback(R.menu.selection_action_mode_menu) { menuItem ->
            when (menuItem?.itemId) {
                R.id.delete_item -> {
                    mainViewModel.deleteItem(userEntity)
                }
                R.id.edit_item -> {
                    mainViewModel.updateItem(userEntity)
                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            startActionMode(actionModeCallback, ActionMode.DEFAULT_HIDE_DURATION)
        } else {
            startActionMode(actionModeCallback)
        }
    }

}