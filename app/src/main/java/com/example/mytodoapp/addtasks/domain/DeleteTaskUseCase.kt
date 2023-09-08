package com.example.mytodoapp.addtasks.domain

import com.example.mytodoapp.addtasks.data.TaskRepository
import com.example.mytodoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val repository: TaskRepository){
    suspend operator fun invoke(taskModel: TaskModel)=repository.deleteTask(taskModel)
}