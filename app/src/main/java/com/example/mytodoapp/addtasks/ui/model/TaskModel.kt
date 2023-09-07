package com.example.mytodoapp.addtasks.ui.model

data class TaskModel(
    val task: String,
    var selected: Boolean,
    val id: Long = System.currentTimeMillis()
)