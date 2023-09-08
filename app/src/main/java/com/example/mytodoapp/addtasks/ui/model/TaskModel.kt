package com.example.mytodoapp.addtasks.ui.model

import com.example.mytodoapp.addtasks.data.TaskEntity

data class TaskModel(
    val task: String,
    var selected: Boolean,
    val id: Int = System.currentTimeMillis().hashCode()
)

fun TaskEntity.toUI()= TaskModel(task = task, selected = selected, id = id)