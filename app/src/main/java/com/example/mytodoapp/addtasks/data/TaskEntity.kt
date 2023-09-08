package com.example.mytodoapp.addtasks.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskEntity(
    @PrimaryKey
    @ColumnInfo(name ="id") val id: Int,
    @ColumnInfo(name = "task") var task: String,
    @ColumnInfo(name ="selected") var selected: Boolean
)