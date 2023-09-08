package com.example.mytodoapp.addtasks.domain

import com.example.mytodoapp.addtasks.data.TaskRepository
import com.example.mytodoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke(): Flow<List<TaskModel>> {
        return taskRepository.tasks
    }
}