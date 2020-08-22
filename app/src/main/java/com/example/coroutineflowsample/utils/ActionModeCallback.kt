package com.example.coroutineflowsample.utils

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.MenuRes

class ActionModeCallback(
    @MenuRes private val menuRes: Int,
    private val selection: (MenuItem?) -> Unit
) : ActionMode.Callback {

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        val menuInflater = mode?.menuInflater
        menuInflater?.inflate(menuRes, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return false
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        selection(item)
        mode?.finish()
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        mode?.finish()
    }
}