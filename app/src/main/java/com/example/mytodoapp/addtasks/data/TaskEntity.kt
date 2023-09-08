package com.example.mytodoapp.addtasks.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mytodoapp.addtasks.ui.model.TaskModel

@Entity(tableName = "task_table")
data class TaskEntity(
    @PrimaryKey
    @ColumnInfo(name ="id") val id: Int,
    @ColumnInfo(name = "task") var task: String,
    @ColumnInfo(name ="selected") var selected: Boolean
)

fun TaskModel.toDB() = TaskEntity(id=id, task =  task, selected = selected)