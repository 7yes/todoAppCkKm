package com.example.mytodoapp.addtasks.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor() : ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog
    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTasksCreated(it: String) {
        Log.d("tas", it)
        _showDialog.value = false
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }
}
