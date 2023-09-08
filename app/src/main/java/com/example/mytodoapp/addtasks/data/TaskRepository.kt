package com.example.mytodoapp.addtasks.data

import com.example.mytodoapp.addtasks.ui.model.TaskModel
import com.example.mytodoapp.addtasks.ui.model.toUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao:TaskDao){

    val tasks: Flow<List<TaskModel>> = taskDao.getTasks().map { items->
    items.map {
        it.toUI()
    }

    }
    suspend fun add(taskModel: TaskModel){
        taskDao.addTask(taskModel.toDB())
    }

    suspend fun update(taskModel: TaskModel) = taskDao.updateTask(taskModel.toDB())

    suspend fun deleteTask(taskModel: TaskModel) = taskDao.deleteTask(taskModel.toDB())
}