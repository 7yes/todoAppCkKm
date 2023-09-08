package com.example.mytodoapp.addtasks.domain

import com.example.mytodoapp.addtasks.data.TaskRepository
import com.example.mytodoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.add(taskModel)
    }
}